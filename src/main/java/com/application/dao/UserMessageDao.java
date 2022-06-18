package com.application.dao;

import com.application.entity.UserMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMessageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMessage record);

    int insertSelective(UserMessage record);

    UserMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMessage record);

    int updateByPrimaryKey(UserMessage record);

    //获取10条信息
    List<UserMessage> get20Message(@Param("fromAccount") Integer fromAccount, @Param("toAccount") Integer toAccount, @Param("page") Integer page);
}