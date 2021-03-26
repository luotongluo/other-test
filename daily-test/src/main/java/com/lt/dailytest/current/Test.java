package com.lt.dailytest.current;

/**
 * @author tong.luo
 * @description Test
 * @date 2021/3/24 14:48
 */
public class Test {
    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
        long l = Runtime.getRuntime().freeMemory();
        long l1 = Runtime.getRuntime().maxMemory();
        long l2 = Runtime.getRuntime().totalMemory();
        System.out.println("totalMemory:" + l2 / 1000D + " \t maxMemory:" + l1 / 1000D + " \t freeMemory:" + l / 1000D);

        String name = Test.class.getName();
        System.out.println(name);
    }
}
