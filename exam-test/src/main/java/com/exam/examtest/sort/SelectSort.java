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

    /**
     * 选择排序，在末排序序列中找到最大 最小元素，存放到排序序列的起始位置，然后再从剩余排未排序元素中继续寻找最值元素，让后放到
     * 已排序序列的末尾。以此类推，知道所有元素均排序完成
     *
     * @param a
     */
    private static void selectSort(int[] a) {

        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            int minVal = a[i];
            for (int j = i + 1; j < a.length; j++) {
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
