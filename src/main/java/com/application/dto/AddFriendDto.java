package com.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author chenLiang
 * @Date 2022/6/15 18:33
 */
@Data
public class AddFriendDto {
    private Integer id;

    private String name;

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
}
