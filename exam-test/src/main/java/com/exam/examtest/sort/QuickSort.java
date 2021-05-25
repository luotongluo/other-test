package com.exam.examtest.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;

/**
 * @author tong.luo
 * @description QuickSort
 * @date 2021/4/8 15:31
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 7, 6, 4, 1, 0, 2, 9, 10, 8};
        sort(arr);
        System.out.println(JSON.toJSONString(Arrays.asList(arr)));
    }

    /**
     * quick sort
     *
     * @param arr
     */
    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int start = arr[i];
            int index = 0;
            //从前往后查找，如果找到的数据比当前的数据小，则交换当前位置，并继续往后找
            for (int j = i ; j < arr.length - 1; j++) {
                if (start > arr[j]) {
                    int temp = arr[index];
                    arr[index] = arr[j];
                    arr[j] = temp;
                    index = j;
                }
            }
            //从后往前找，大的位于当前值的后面
            for (int j = 0; j < arr.length - 1; j++) {
                if (start < arr[j]) {
                    int temp = arr[index];
                    arr[index] = arr[j];
                    arr[j] = temp;
                    index = j;
                }
            }
        }
    }
}
