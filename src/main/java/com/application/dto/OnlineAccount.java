package com.application.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author chenLiang
 * @Date 2022/6/14 12:16
 */
@Data
public class OnlineAccount {
    private String code;
    private List<Integer> onlineAccount;
}
