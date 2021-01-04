package com.lt.springcloudtest.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tong.luo
 * @description TestBean
 * @date 2021/1/4 14:52
 */
public class TestBean implements Serializable {
    private static final long serialVersionUID = -4116925219370846429L;
    private String name;
    private Integer age;
    private String otherName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public TestBean() {
    }

    public TestBean(String name, Integer age, String otherName, Date createTime) {
        this.name = name;
        this.age = age;
        this.otherName = otherName;
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }
}
