package com.lt.dailytest.other;

import com.lt.dailytest.utils.common.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author tong.luo
 * @description TimeTest
 * @date 2021/11/1 14:37
 */
public class TimeTest {
    @Test
    public void timeTest(){
        String time = "2021-11-02";
        Date date = DateUtils.parseDate(time, DateUtils.yyyy_MM_dd);
        String s = DateUtils.formatDate(date, DateUtils.yyyyMMdd);
        System.out.println(s);
    }
}
