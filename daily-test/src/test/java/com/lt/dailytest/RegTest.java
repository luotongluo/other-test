package com.lt.dailytest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tong.luo
 * @description RegTest
 * @date 2021/3/19 14:59
 */
public class RegTest {
    public static void main(String[] args) {
        String ss = "非必填，自动带出可编辑；1-40字节，字母数字中文及：~`!@#$%^*()_+[]\\;',./?:\"{}|~·！@#￥%……*（）——+{}|：“《》？【】、；‘，。/  组合";
        String regEx = "[中]*";
        Matcher matcher = Pattern.compile(regEx).matcher(ss);
        System.out.println(matcher.replaceAll(""));
    }
}
