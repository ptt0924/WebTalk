package com.application.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.application.config.GetHttpSessionConfigurator;
import com.application.dto.OnlineAccount;
import com.application.entity.AddFriend;
import com.application.dto.Message;
import com.application.entity.Friends;
import com.application.service.AddFriendService;
import com.application.service.FriendsService;
import com.application.service.UserMessageService;
import com.application.service.UserService;
import com.application.utils.Render;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author chenLiang
 * @Date 2022/6/3 21:12
 */
@ServerEndpoint(value = "/chat/{user}",configurator = GetHttpSessionConfigurator.class)
public class WebSocket {
    /**
     * 与某个客户端连接通话，需要通过它来给客户端发送消息
     */
    private Session session;

   public static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();

   private UserService userService = new UserService();

   private UserMessageService userMessageService = new UserMessageService();

   private FriendsService friendsService = new FriendsService();

   private AddFriendService addFriendService = new AddFriendService();

    /**
     * 标识当前连接客户端的用户名
     */
    private String account;

    private String password;

    public String getPassword(){
        return password;
    }

    public String getAccount() {
        return account;
    }

    /**
     * 连接建立成功调用的方法
     * @param session
     * @param user
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "user") String user) {
        String[] accountPwd = user.split(",");
        account = accountPwd[0];
        password = accountPwd[1];
        System.out.println(account);
        System.out.println(password);
        this.session = session;
        OnlineAccount onlineAccount = new OnlineAccount();
        //把自己的连接也要放入，是开放的
        webSocketMap.put(account, this);
        System.out.println("有新的连接加入，当前在线人数为" + webSocketMap.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(account);
        //同时通知在线好友你离线了
        List<Friends> list = friendsService.getAllFriends(Integer.parseInt(account));
        for(Friends friends:list){
            if(webSocketMap.containsKey(friends.getFriendAccount()+"")){
                Message message = new Message();
                message.setCode("5");
                message.setToAccount(friends.getFriendAccount()+"");
                message.setFromAccount(account);
                message.setMes("该用户离线");
                appointSending(friends.getFriendAccount()+"", JSON.toJSONString(message));
            }
        }
        System.out.println("有一连接关闭，当前在线人数为" + webSocketMap.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     */
    @OnMessage
    public void OnMessage(String message) {
        System.out.println(message);
        Message message1 = JSONObject.parseObject(message,Message.class);
        System.out.println("收到消息：" + message);
        if(message1.getCode().equals("0")){  //0：单聊
            //在线则发送
            if(webSocketMap.containsKey(message1.getToAccount()))
                appointSending(message1.getToAccount(),message);
            //保存信息
            userMessageService.saveMessage(message1);
        }else if(message1.getCode().equals("1")) { //1:是否在线
            int account = Integer.parseInt(message1.getFromAccount());
            //首先得到自己的好友列表
            List<Friends> list = friendsService.getAllFriends(account);
            if(list==null){
                message1.setToAccount("0");
                appointSending(message1.getFromAccount(),JSONObject.toJSONString(message1));
            }else{
            List<Render> renderList = new ArrayList<>();
            //得到之后判断哪些在线，哪些没在线
            for(Friends friends:list) {
                if (webSocketMap.containsKey(friends.getFriendAccount()+"")) {
                    renderList.add(new Render(friends.getFriendAccount(), 1));
                    //同时通知好友你上线了
                    message1.setToAccount("1");
                    message1.setMes(message1.getFromAccount());
                    appointSending(friends.getFriendAccount() + "", JSONObject.toJSONString(message1));
                    renderList.add(new Render(friends.getFriendAccount(),1));
                } else {
                    renderList.add(new Render(friends.getFriendAccount(), 0));
                }
            }
                //最后返回该用户的在线列表
                message1.setToAccount("2");
                message1.setMes(JSONObject.toJSONString(renderList));
                appointSending(message1.getFromAccount(),JSONObject.toJSONString(message1));
            }
        }else if(message1.getCode().equals("2")){  //2同意添加好友
            friendsService.saveFriends(message1);
            //先查找出添加信息
            AddFriend addFriend = addFriendService.getAddFriend(Integer.parseInt(message1.getFromAccount()),Integer.parseInt(message1.getToAccount()  ));
            message1.setMes(addFriend.getMessage());
            //在线则那面直接在好友列表里添加
            if(webSocketMap.containsKey(message1.getFromAccount())){
                appointSending(message1.getFromAccount(),JSONObject.toJSONString(message1));
            }
        }else if(message1.getCode().equals("3")){ //请求添加好友
            //在线则直接推送过去
            if(webSocketMap.containsKey(message1.getToAccount()))
                appointSending(message1.getToAccount(),message);
            //数据库保存
            AddFriend addFriend = new AddFriend();
            addFriend.setMessage(message1.getMes());  //存入的是备注
            addFriend.setFromAccount(Integer.parseInt(message1.getFromAccount()));
            addFriend.setToAccount(Integer.parseInt(message1.getToAccount()));
            addFriend.setSendTime(message1.getDate());
            addFriend.setStatus("0");
            addFriendService.insert(addFriend);
        }else if(message1.getCode().equals("4")){ //发送图片

        }else if(message1.getCode().equals("5")){ //发送文件

        }else if(message1.getCode().equals("6")){ //是否在线

        }
    }

    /**
     * 发生错误时调用
     * @param t
     */
    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
        System.out.println("发生错误");
    }

    /**
     * 群发消息
     * @param message
     */
    public void GroupSending(String message){
        //群发
        for(String name:webSocketMap.keySet()){
            try {
                webSocketMap.get(name).session.getBasicRemote().sendText(message);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定发送
     * @param toAccount
     * @param jsonString
     */
    public void  appointSending(String toAccount,String jsonString){
        try {
            webSocketMap.get(toAccount).session.getBasicRemote().sendText(jsonString);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ConcurrentHashMap<String, WebSocket> getWebSocketMap() {
        return webSocketMap;
    }

    /**
     * 发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message){
        System.out.println("消息===="+message);
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
