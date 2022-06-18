package com.application.dto;

import com.application.entity.UserMessage;
import lombok.Data;

import java.util.List;

/**
 * @Author chenLiang
 * @Date 2022/6/13 15:25
 */
@Data
public class MessageList {
    //好友账号
    private Integer account;
    //消息
    private List<UserMessage> messageList;
    //未读消息多少条
    private Integer count;
}
