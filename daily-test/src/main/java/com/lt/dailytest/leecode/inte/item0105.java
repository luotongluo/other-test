package com.lt.dailytest.leecode.inte;

import java.math.BigDecimal;

/**
 * @author tong.luo
 * @description item0105
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，
 * 编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * @date 2021/5/6 17:45
 */
public class item0105 {
    public static boolean oneEditAway(String first, String second) {
        //先判断 两个字符串的长度是否小于1
        int s1Len = first.length();
        int s2Len = second.length();
        if(Math.abs(s1Len - s2Len) > 1){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        String s1 = "intention";
        String s2 = "execution";
        oneEditAway(s1,s2);
    }
}
