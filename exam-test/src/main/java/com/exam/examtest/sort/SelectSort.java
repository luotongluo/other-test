package com.exam.examtest.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author tong.luo
 * @description SelectSort
 * @date 2021/2/19 17:35
 */
public class SelectSort {
    public static void main(String[] args) {
        int a[] = {5, 4, 3, 2, 1, 7, 8, 9, 0};
        selectSort(a);
        System.out.println(JSON.toJSONString(a));
    }

    private static void selectSort(int[] a) {

        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            int minVal = a[i];
            for (int j = i +1; j < a.length; j++) {
                if (a[j] < minVal) {
                    minIndex = j;
                    minVal = a[j];
                }
            }
            if (minIndex != i) {
                a[minIndex] = a[i];
                a[i] = minVal;
            }
        }
    }
}
