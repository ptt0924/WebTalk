package com.application.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * add_friend
 * @author 
 */
@Data
public class AddFriend implements Serializable {
    private Integer id;

    private Integer fromAccount;

    private Integer toAccount;

    /**
     * 发送消息
     */
    private String message;

    /**
     * 请求时间
     */
    private Date sendTime;

    /**
     * 同意/拒绝
     */
    private String status;

    private static final long serialVersionUID = 1L;
}