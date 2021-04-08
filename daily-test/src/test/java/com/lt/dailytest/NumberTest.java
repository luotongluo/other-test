package com.lt.dailytest;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * @author tong.luo
 * @description NumberTest
 * @date 2021/3/10 10:45
 */
public class NumberTest {
    public static void main(String[] args) {
        double a = 2000.1234;
        String str = NumberFormat.getIntegerInstance(Locale.getDefault()).format(a);     //转为千分符字符串
        System.out.println(str);

        try {
            int b = NumberFormat.getIntegerInstance(Locale.getDefault()).parse(str).intValue();    //转为数字
            System.out.println("" + b);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        byte[] b = new byte[8 * 1024 * 925];
    }
}
