package com.application.service;

import com.application.dao.FriendsDao;
import com.application.dto.Message;
import com.application.entity.AddFriend;
import com.application.entity.Friends;
import com.application.utils.MybatisUtil;

import java.util.List;

/**
 * @Author chenLiang
 * @Date 2022/5/30 10:45
 */
public class FriendsService {

    FriendsDao friendsDao = MybatisUtil.getSqlSession().getMapper(FriendsDao.class);
    AddFriendService addFriendService = new AddFriendService();

    /**
     * 获取所有的朋友
     * @param userId
     * @return
     */
    public List<Friends> getAllFriends(Integer userAccount){
        return friendsDao.getAllFriends(userAccount);
    }


    /**
     * 得到指定好友
     * @param fromAccount
     * @param toAccount
     * @return
     */
//    public Friends getFriends(Integer fromAccount,Integer toAccount){
//        return friendsDao.getFriends(fromAccount,toAccount);
//    }

    public int updateFriend(Integer fromAccount,Integer toAccount,String remark){
        return friendsDao.updateFriends(fromAccount,toAccount,remark);
    }

    /**
     * 保存好友
     * @param message
     */
    public void saveFriends(Message message){
        //先查找出添加信息
        AddFriend addFriend = addFriendService.getAddFriend(Integer.parseInt(message.getFromAccount()),Integer.parseInt(message.getToAccount()  ));
        //设置请求好友
        Friends friends = new Friends();
        Integer fromAccount = Integer.parseInt(message.getFromAccount());
        Integer toAccount = Integer.parseInt(message.getToAccount());
        friends.setFriendAccount(fromAccount);
        friends.setUserAccount(toAccount);
        friends.setTime(message.getDate());
        friends.setRemark(message.getMes());
        friends.setReadTime(message.getDate());
        //设置同意好友
        Friends friends1 = new Friends();
        friends1.setUserAccount(fromAccount);
        friends1.setFriendAccount(toAccount);
        friends1.setRemark(addFriend.getMessage());
        friends1.setTime(message.getDate());
        friends1.setReadTime(message.getDate());
        System.out.println(friends);
        System.out.println(friends1);
        friendsDao.insert(friends);
        friendsDao.insert(friends1);
        addFriendService.updateAddFriendStatus(fromAccount,toAccount,"1");
    }



}
