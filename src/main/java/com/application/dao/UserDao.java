package com.application.dao;

import com.application.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUser(String username,String password);

    User isExistsUser(@Param("account") Integer account,@Param("password") String password);

    User isExistsAccount(@Param("account") Integer account);

    User isExistsPhone(Integer phone);

    User getUser(@Param("account") Integer account,@Param("password") String password);

    User searchUser(@Param("account") Integer account);
}