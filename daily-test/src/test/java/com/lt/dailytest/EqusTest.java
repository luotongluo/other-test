package com.lt.dailytest;


/**
 * @author tong.luo
 * @description EqusTest
 * @date 2021/3/5 10:45
 */
public class EqusTest {
    public static void main(String[] args) {
        String equals = "PASS";
        System.out.println(equals.hashCode());
        boolean equals1 = equals.equals(null);
        System.out.println(equals1);
        System.out.println(getHashCode(equals));
    }
    public static Integer getHashCode(String val){
        int h;
        return (h = val.hashCode()) ^ (h >>> 16);
    }
}
