package com.verena.s1y.onemorediary.constant;

import lombok.Getter;

@Getter
public enum  Status {

    OK(200 , "操作成功"),

    UNKNOWN_ERROR(1000,"未知异常"),

    JSON_DATA_ERROR(1002,"JSON Data 对象异常"),

    SQL_INSERT_ERROR(40001,"数据库插入异常"),

    WECHAT_GET_SECRET_ERROR(1001,"微信获取唯一ID异常");

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
