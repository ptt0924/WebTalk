package com.application.service;

import com.application.dao.UserMessageDao;
import com.application.dto.MessageList;
import com.application.entity.Friends;
import com.application.dto.Message;
import com.application.entity.UserMessage;
import com.application.utils.MybatisUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author chenLiang
 * @Date 2022/5/30 11:05
 */
public class UserMessageService {

    FriendsService friendsService = new FriendsService();

    UserMessageDao userMessageDao = MybatisUtil.getSqlSession().getMapper(UserMessageDao.class);

    /**
     * 登录后，获取信息 获取信息 包括未读和已读
     * @param toAccount
     * @return
     */
    public HashMap<String,MessageList> getMessage(String to, Integer page){
        int toAccount = Integer.parseInt(to);
        List<Friends> friends = friendsService.getAllFriends(toAccount);
//        List<List<UserMessage>> listMessage = new ArrayList<>();
        HashMap<String,MessageList>  map = new HashMap<>();
        List<MessageList> list = new ArrayList<>();
        for(Friends friend : friends){
            MessageList messageList = new MessageList();
            messageList.setAccount(friend.getFriendAccount());
            //得到20条最近的消息
            messageList.setMessageList(get20Message(toAccount,friend.getFriendAccount(),page));
            //todo 没有弄未读   如果时间早于10条最早的，就调用比较时间的接口
            //先设置为10条未读
            messageList.setCount(10);
            map.put(friend.getFriendAccount()+"",messageList);
        }
//        System.out.println(map);
        return map;
    }

    //
    public List<UserMessage> get20Message(Integer fromAccount, Integer toAccount, Integer page){
        return userMessageDao.get20Message(fromAccount,toAccount,page*20);
    }

    //获取指定某个人的历史记录
    public List<UserMessage> getMessage(Integer fromAccount,Integer toAccount,String date){

        return null;
    }


    //保存文本信息
    public void saveMessage(Message message){
        UserMessage userMessage = new UserMessage();
        userMessage.setFromAccount(Integer.parseInt(message.getFromAccount()));
        userMessage.setToAccount(Integer.parseInt(message.getToAccount()));
        userMessage.setSendTime(message.getDate());
        userMessage.setText(message.getMes());
        userMessageDao.insert(userMessage);
    }
}
