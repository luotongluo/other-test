package com.lt.dailytest.sort;

import java.util.Arrays;

/**
 * @author tong.luo
 * @description RadixSort
 * 基数排序
 * @date 2021/3/12 10:52
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] sortdata = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81};
        sort(sortdata,2);
        System.out.println(Arrays.toString(sortdata));
    }

    /**
     * //d表示最大的数有多少位
     *
     * @param number
     * @param d
     */
    public static void sort(int[] number, int d) {
        int k = 0;
        int n = 1;
        int m = 1; //控制键值排序依据在哪一位
        int[][] temp = new int[10][number.length]; //数组的第一维表示可能的余数0-9
        int[] order = new int[10]; //数组order[i]用来表示该位是i的数的个数
        while (m <= d) {
            for (int i = 0; i < number.length; i++) {
                int lsd = ((number[i] / n) % 10);
                temp[lsd][order[lsd]] = number[i];
                order[lsd]++;
            }
            for (int i = 0; i < 10; i++) {
                if (order[i] != 0)
                    for (int j = 0; j < order[i]; j++) {
                        number[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }
}