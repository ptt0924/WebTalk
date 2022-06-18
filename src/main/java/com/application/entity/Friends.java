package com.application.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * friends
 * @author 
 */
@Data
public class Friends implements Serializable {
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

    private static final long serialVersionUID = 1L;
}