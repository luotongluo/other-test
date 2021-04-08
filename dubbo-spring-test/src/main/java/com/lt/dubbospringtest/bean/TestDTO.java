package com.lt.dubbospringtest.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tong.luo
 * @description testDTO
 * @date 2021/3/1 17:19
 */
public class TestDTO implements Serializable {
    private static final long serialVersionUID = -1316472349220259195L;
    private Integer id;
    private String name;
    @JSONField(name = "time", format = "yyyy-MM-dd")
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
