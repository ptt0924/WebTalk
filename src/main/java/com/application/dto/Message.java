package com.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author chenLiang
 * @Date 2022/6/12 21:49
 */
@Data
public class Message {
    private String code;
    private String mes;
    private String fromAccount;
    private String toAccount;
    private Date date;
    private String name;
}
