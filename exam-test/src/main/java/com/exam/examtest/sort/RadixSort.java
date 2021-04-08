package com.exam.examtest.sort;

/**
 * @author tong.luo
 * @description RadixSort
 * 基数排序
 * @date 2021/4/8 10:35
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81};
        radixSort(arr);
    }

    /**
     * 基数排序
     *
     * @param arr
     */
    private static void radixSort(int[] arr) {
        //求出待排序中的数据最长是多少位的
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            maxLength = String.valueOf(arr[i]).length();
        }
        //初识的余数 为10；
        int remainder = 10;
        int[][] doubleArr = new int[10][];
        int length = String.valueOf(remainder).length();
        while (length <= maxLength){
            for (int i : arr) {
                int radixVal = i % remainder;
                for (int j = 0; j < arr.length; j++) {
                    Integer val = doubleArr[radixVal][j];
                    if (val == null) {
                        doubleArr[radixVal][j] = arr[i];
                    }
                }
            }
            remainder = remainder * 10;
        }

    }
}
