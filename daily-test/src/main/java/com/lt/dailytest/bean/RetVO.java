package com.lt.dailytest.bean;

import java.io.Serializable;

/**
 * @author tong.luo
 * @description RetVO
 * @date 2021/3/8 17:07
 */
public class RetVO<T> implements Serializable {
    private static final long serialVersionUID = 4087908700685218023L;

    private Integer code = 0;
    private String msg;
    private T data;

    public RetVO() {
    }

    public RetVO(Integer code, String msg, T t) {
        this.code = code;
        this.msg = msg;
        this.data = t;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
