package com.lt.dailytest.exception;

/**
 * @author tong.luo
 * @description BusinessException
 * @date 2021/3/8 17:14
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -6733364661567308818L;
    private Integer code;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer code,String msg) {
        super(msg);
        this.code = code;
    }
}
