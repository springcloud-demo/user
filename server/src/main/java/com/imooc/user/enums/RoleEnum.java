package com.imooc.user.enums;

import lombok.Getter;

/**
 * @ClassName: RoleEnum
 * @Description: TODO
 * @Author: ZhangChen
 * @Date: 2019/6/29 16:12
 **/
@Getter
public enum RoleEnum {
    BUYER(1,"买家"),
    SELLER(2,"卖家"),
    ;
    private Integer code;
    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
