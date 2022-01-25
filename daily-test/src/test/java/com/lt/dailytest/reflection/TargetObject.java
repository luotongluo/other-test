package com.lt.dailytest.reflection;

/**
 * @author tong.luo
 * @description
 * @date 2022/1/24 17:14
 */
public class TargetObject {
    private String value;

    public TargetObject() {
        value = "JavaGuide";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }
}
