package com.lt.dailytest.othertest.leecode.curr;

/**
 * @author 12828
 * @description CompareTwoIntNum
 * 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
 * @date 2021/12/11 23:03
 */
public class CompareTwoIntNum {
    public static void main(String[] args) {

    }

    public int maximum(int a, int b) {
        int num = a > b ? a : b;
        return a > b ? a : b;
    }

    /**
     * 使用异或的方式进行
     * @param a
     * @param b
     * @return
     */
    public int maximum1(int a, int b) {
        int k = (int) (((long) a - (long) b) >>> 63 & 1);
        return a * (1 - k) + b * k;
    }
}
