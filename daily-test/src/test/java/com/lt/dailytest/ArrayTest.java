package com.lt.dailytest;

import java.util.Arrays;

/**
 * @author tong.luo
 * @description ArrayTest
 * @date 2021/4/26 17:15
 */
public class ArrayTest {
    public static void main(String[] args) {
        String[] sourceArray = {"目标1","目标2"};
        String[] targetArray = {"目标1","目标2"};
        boolean equals = Arrays.equals(sourceArray, targetArray);
        System.out.println(equals);
    }
}
