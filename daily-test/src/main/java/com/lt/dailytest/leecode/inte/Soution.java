package com.lt.dailytest.leecode.inte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author tong.luo
 * @description Soution
 * @date 2021/5/6 16:56
 */
public class Soution {
    public static boolean CheckPermutation(String s1, String s2) {
        if(s1.length() != s2.length()){
            return false;
        }
        char[] chars = s1.toCharArray();
        Arrays.sort(chars);
        char[] chars1 = s2.toCharArray();
        Arrays.sort(chars1);
        return new String(chars).equals(new String(chars1));
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "bca";
        boolean b = CheckPermutation(s1, s2);
        System.out.println(b);
    }
}
