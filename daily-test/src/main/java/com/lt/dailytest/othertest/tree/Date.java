package com.lt.dailytest.othertest.tree;

import com.alibaba.fastjson.JSON;

import java.time.LocalTime;

/**
 * @author tong.luo
 * @description Date
 * @date 2021/5/31 21:23
 */
public class Date {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();

        System.out.println(JSON.toJSON(now));
    }
}
