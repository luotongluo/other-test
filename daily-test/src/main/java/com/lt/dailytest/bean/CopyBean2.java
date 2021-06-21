package com.lt.dailytest.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author tong.luo
 * @description CopyBean1
 * @date 2021/5/31 17:01
 */
public class CopyBean2 implements Serializable {
    private static final long serialVersionUID = 628149093800155612L;
    private String name;
    private Integer age;
    private Integer anInt;
    private Date date;
    private List<String> stringList;

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

    public Integer getAnInt() {
        return anInt;
    }

    public void setAnInt(Integer anInt) {
        this.anInt = anInt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
