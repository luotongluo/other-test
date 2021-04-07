package com.exam.examtest.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * @author tong.luo
 * @description BubbleSort
 * @date 2021/2/19 17:21
 */
public class BubbleSort {
    public static void main(String[] args) {
        int a[] = {1, 4, 2, 5, 6, 3, 9, 8,1,2,3,22,12,3,2,1,2,3,2,23};
        bubbleSort(a);
        System.out.println(JSON.toJSONString(a));
    }

    private static void bubbleSort(int[] a) {
        int temp = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                //如果前面的数da于后面的数 则进行数据的交换
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }

        }
    }
}
