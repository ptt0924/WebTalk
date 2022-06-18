package com.application.service;

import com.application.dao.UserDao;
import com.application.entity.User;
import com.application.utils.MybatisUtil;


/**
 * @Author chenLiang
 * @Date 2022/5/30 10:46
 */
public class UserService {
    UserDao userDao = MybatisUtil.getSqlSession().getMapper(UserDao.class);

    public User selectByPrimaryKey(Integer id){
        return userDao.selectByPrimaryKey(id);
    }

    public void editUser(User user){
        userDao.updateByPrimaryKey(user);
    }

    public boolean isExistsUser(Integer account,String password) {
        if (userDao.isExistsUser(account, password)!=null)
            return true;
        return false;
    }

    public boolean isExistsAccount(Integer account) {
        if (userDao.isExistsAccount(account)!=null)
            return true;
        return false;
    }

    public boolean isExistsPhone(Integer phone){
        if(userDao.isExistsPhone(phone)!=null){
            return true;
        }
        return false;
    }

    public void insertUser(User user){
        userDao.insert(user);
    }


    public User getUser(Integer account,String password){
       return  userDao.getUser(account,password);
    }

    public User searchUser(Integer account){
        return userDao.searchUser(account);
    }

    public int updateUser(User user){
        return userDao.updateByPrimaryKey(user);
    }

    public User selectByPrimaryKey(User user){
        return userDao.selectByPrimaryKey(user.getId());
    }

}
