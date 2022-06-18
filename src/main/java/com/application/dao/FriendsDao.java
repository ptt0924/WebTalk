package com.application.dao;

import com.application.dto.FriendDto;
import com.application.entity.Friends;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("record") Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);

    List<Friends> getAllFriends(Integer userAccount);

//    List<FriendDto> getAllFriendDto(Integer userAccount);

    int updateFriends(@Param("from") Integer fromAccount,@Param("to") Integer toAccount,@Param("remark") String remark);
}