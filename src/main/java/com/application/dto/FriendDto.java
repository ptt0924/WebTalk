package com.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author chenLiang
 * @Date 2022/6/15 21:44
 */
@Data
public class FriendDto {

    private Integer id;

    /**
     * 用户账户
     */
    private Integer userAccount;

    /**
     * 好友账户
     */
    private Integer friendAccount;

    /**
     * 成为好友的时间
     */
    private Date time;

    /**
     * 备注
     */
    private String remark;

    /**
     * 上一次读取好友信息的时间
     */
    private Date readTime;

    private String name;
}
