package com.lt.dailytest.bean;

/**
 * @author tong.luo
 * @description BeanEnum
 * @date 2021/3/8 17:09
 */
public enum CodeEnum {

    SUCCESS(SysEnum.SUCCESS_CODE, SysEnum.SUCCESS_MSG),
    ERROR(99999, "请求失败"),
    ;
    private final Integer code;
    private final String msg;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
