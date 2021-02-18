package com.lt.factorytest.space;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author tong.luo
 * @description SpaceSort
 * @date 2021/2/9 17:06
 */
public class SpaceSort {
    public static int arrayLen = 1000000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] a = new int[arrayLen];
        int[] old = new int[arrayLen];
        HashMap<Integer, Object> hashMap = new HashMap<>(16);
        int count = 0;
        while (count < a.length) {
            int value = (int) (Math.random() * arrayLen * 10) + 1;
            if (hashMap.get(value) == null) {
                hashMap.put(value, value);
                a[count] = value;
                count++;
            }
        }
        System.arraycopy(a, 0, old, 0, a.length);
        long timeMillis = System.currentTimeMillis();

        Arrays.sort(a);
        System.out.println("arrays sort spend :" + (System.currentTimeMillis() - timeMillis));

        System.arraycopy(old, 0, a, 0, old.length);
        timeMillis = System.currentTimeMillis();
        spaceToTime(a);
        System.out.println("all : arrays sort spend :" + (System.currentTimeMillis() - timeMillis));

    }

    private static void spaceToTime(int[] a) {
        int i = 0;
        int max = a[0];
        int length = a.length;
        //找出最大值
        for (int j = 0; j < length; j++) {
            if (a[j] > max) {

                max = a[j];
            }
        }
        //分配临时空间
        int[] temp = new int[max + 1];
        for (i= 0;i < length; i++) {
            //用索引下表来标识数字大小
            temp[a[i]] = a[i];
        }
        int m =0;
        int max1 = max + 1;
        //线性复杂度
        for (i = 0; i < max1; i++) {
            if (temp[i] > 0){
                a[m ++ ] = temp[i];
            }
        }

    }
}
