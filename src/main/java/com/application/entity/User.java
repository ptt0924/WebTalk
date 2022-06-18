package com.application.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer id;

    /**
     * 昵称
     */
    private String name;

    /**
     * 账号
     */
    private Integer account;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private Integer phone;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 头像地址
     */
    private String avatatUrl;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 职业（字典）
     */
    private Integer occupation;

    /**
     * 家乡
     */
    private String hometown;

    /**
     * 状态0：在线，1离线
     */
    private String status;

    private static final long serialVersionUID = 1L;
}