package com.application.service;

import com.application.dao.AddFriendDao;
import com.application.dto.AddFriendDto;
import com.application.entity.AddFriend;
import com.application.entity.User;
import com.application.utils.DruidDataSourceFactory;
import com.application.utils.MybatisUtil;

import java.util.List;

/**
 * @Author chenLiang
 * @Date 2022/6/14 16:09
 */
public class AddFriendService {
    UserService userService = new UserService();
    private AddFriendDao addFriendDao = MybatisUtil.getSqlSession().getMapper(AddFriendDao.class);

    public void insert(AddFriend addFriend){
        addFriendDao.insert(addFriend);
    }

    public User searchFriend(Integer account){
        return userService.searchUser(account);
    }

    public List<AddFriendDto> getAddFriendMessage(Integer account){
        return addFriendDao.getAddFriendMessage(account);
    }

    public int deleteAddFriend(Integer fromAccount,Integer toAccount){
        return addFriendDao.deleteAddFriend(fromAccount,toAccount);
    }

    public AddFriend getAddFriend(Integer fromAccunt,Integer toAccount){
        return addFriendDao.getAddFriend(fromAccunt,toAccount);
    }

    public void updateAddFriendStatus(Integer fromAccount,Integer toAccount,String status){
        addFriendDao.updateAddFriendStatus(fromAccount,toAccount,status);
    }

    public boolean isAddRequest(Integer fromAccount,Integer toAccount){
        if(addFriendDao.getAddFriend(fromAccount,toAccount)!=null)
            return true;
        return false;
    }
}
