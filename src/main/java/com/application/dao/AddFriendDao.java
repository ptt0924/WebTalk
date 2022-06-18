package com.application.dao;

import com.application.dto.AddFriendDto;
import com.application.entity.AddFriend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddFriendDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AddFriend record);

    int insertSelective(AddFriend record);

    AddFriend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AddFriend record);

    int updateByPrimaryKey(AddFriend record);

    List<AddFriendDto> getAddFriendMessage(Integer account);

    int deleteAddFriend(@Param("from") Integer fromAccount,@Param("to") Integer toAccount);

    AddFriend getAddFriend(@Param("from") Integer fromAccount,@Param("to") Integer toAccount);

    int updateAddFriendStatus(@Param("from") Integer fromAccount,@Param("to") Integer toAccount,@Param("status") String status);
}