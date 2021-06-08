package com.lt.dailytest.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tong.luo
 * @description DateUtils
 * @date 2021/6/4 10:25
 */
public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    public static Date parseDate(String dateValue, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(dateValue);
        }
        catch (Exception e) {
            logger.error("Warning in DateUtil#parseDate.", e);
            return null;
        }
    }
}
