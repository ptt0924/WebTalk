package com.application.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user_message
 * @author 
 */
@Data
public class UserMessage implements Serializable {
    private Integer id;

    /**
     * 发送方
     */
    private Integer fromAccount;

    /**
     * 接收方
     */
    private Integer toAccount;

    /**
     * 文本
     */
    private String text;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 发送时间
     */
    private Date sendTime;

    private static final long serialVersionUID = 1L;
}