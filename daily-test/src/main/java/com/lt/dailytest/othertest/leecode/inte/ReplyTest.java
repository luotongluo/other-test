package com.lt.dailytest.othertest.leecode.inte;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tong.luo
 * @description ReplyTest
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 * <p>
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 * <p>
 * 回文串不一定是字典当中的单词。
 * @date 2021/5/6 17:32
 */
public class ReplyTest {
    public boolean canPermutePalindrome(String s) {
        //如果字符串中的字符出现两次则认为是重复的，如果判断完成之后剩余的字符大于1个 则不认为是回文串
        char[] chars = s.toCharArray();
        Set<Character> objectSet = new HashSet<>();
        for (char aChar : chars) {
            //如果集合中没有包含的时候则将元素添加到集合中
            if (!objectSet.contains(aChar)) {
                objectSet.add(aChar);
            } else {
                objectSet.remove(aChar);
            }

        }
        if (objectSet.size() > 1) {
            return false;
        }

        return true;
    }

    public boolean canPermutePalindrome1(String s) {
        char[] chars = s.toCharArray();
        int[] count = new int[128];
        int flag = 0;
        for (int i = 0; i < chars.length; i++) {
            //当前为奇数，说明这次+1之后就为偶数，符合回文要求。flag--。
            if ((count[chars[i]] & 1) == 1) {
                flag--;
            } else {//当前为偶数，说明这次-1之后就为奇数，可能不符合回文要求，先记录下来。flag++。
                flag++;
            }
            count[chars[i]]++;
        }
        //一串回文中，至多有一个出现次数为奇数的字符。
        if (flag > 1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
