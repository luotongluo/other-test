package com.lt.dailytest.othertest.tree;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

/**
 * @author tong.luo
 * @description Date
 * @date 2021/5/31 21:23
 */
public class Date {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();

        System.out.println(JSON.toJSON(now));

        testMonthData();
    }

    public static void testMonthData(){
//获取当月第一天和最后一天
        Calendar cale = null;
        cale = Calendar.getInstance();
// 获取当月第一天和最后一天
        SimpleDateFormat formatTemp = new SimpleDateFormat("yyyy-MM-dd");
        String firstday, lastday;
// 获取当前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = formatTemp.format(cale.getTime());
// 获取当前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = formatTemp.format(cale.getTime());
        System.out.println("firstday:"+firstday+" lastday:"+lastday);
    }
}
