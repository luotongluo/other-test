package com.exam.examtest.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author tong.luo
 * @description InsertSort
 * @date 2021/2/19 17:46
 */
public class InsertSort {
    public static void main(String[] args) {
        int a[] = {1, 4, 2, 5, 6, 3, 9, 8};
        insertSort(a);
        System.out.println(JSON.toJSONString(a));
    }

    private static void insertSort(int[] a) {
        int index = 0;
        int indexval = 0;
        for (int i = 1; i < a.length; i++) {
            index = i - 1;
            indexval = a[i];
            //如果i的值小于前一个值 则进行替换
            while (index > 0 && indexval < a[index]) {
                a[index + 1] = a[index];
                index--;
            }
            if (index + 1 != i) {
                a[index + 1] = indexval;

            }
        }
    }
}
