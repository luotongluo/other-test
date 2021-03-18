package com.lt.dailytest.utils;

/**
 * @author tong.luo
 * @description PermTest
 * 每当常量池饱和时，fullgc总能顺利回收常量池数据，确保程序稳定持续运行
 * @date 2021/3/17 19:15
 */
public class PermTest {
    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            //如果常量池中含有该对象则返回。如果没有则先将string对象加入常量池中，在进行返回
            String intern = String.valueOf(i).intern();
        }
        Runtime.getRuntime().maxMemory();
    }
}
