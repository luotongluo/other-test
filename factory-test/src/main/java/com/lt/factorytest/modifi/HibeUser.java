package com.lt.factorytest.modifi;

import java.io.Serializable;

/**
 * @author tong.luo
 * @description HibeUser
 * @date 2021/2/8 11:24
 */
public class HibeUser implements Serializable {
    private static final long serialVersionUID = 3157934349099984788L;

    private Integer id;
    private String name;
    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
