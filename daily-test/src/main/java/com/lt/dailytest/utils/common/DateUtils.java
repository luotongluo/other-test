package com.lt.dailytest.utils.common;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author tong.luo
 * @description DateUtils
 * @date 2021/6/4 10:25
 */
public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyy_MM_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String yyyy_MM_dd_hh_mm = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date, String pattern) {
        Assert.notNull(date, "传入的参数不能为空");
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date parseDate(String dateValue, String pattern) {
        Assert.notNull(dateValue, "传入的参数不能为空");
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(dateValue);
        } catch (Exception e) {
            logger.error("Warning in DateUtil#parseDate.", e);
            return null;
        }
    }

    /**
     * 获取某天的最大时间
     *
     * @param date 传入日期
     * @return Date
     */
    public static Date getEndOfDay(Date date) {
        Assert.notNull(date, "传入的参数不能为空");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取某天的最小时间
     *
     * @param date 传入日期
     * @return ret
     */
    public static Date getStartOfDay(Date date) {
        Assert.notNull(date, "传入的参数不能为空");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * long 转换为date
     *
     * @param time long
     * @return  return
     */
    public static String longToStringDate(Long time) {
        Assert.notNull(time, "传入的参数不能为空");
        return DateFormatUtils.format(time, DateUtils.yyyy_MM_dd_hh_mm_ss);

    }

    /**
     * 当前时间的前后多少秒 +的为之后的时间  负的为之前的时间
     */
    public static String addSecond(Integer seconds) {
        Assert.notNull(seconds, "传入的参数不能为空");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, seconds);
        return formatDate(calendar.getTime(), yyyy_MM_dd_hh_mm_ss);
    }

    /**
     * 当前时间的前后多少分钟 +的为之后的时间  负的为之前的时间
     */
    public static String addMinute(Integer minutes) {
        Assert.notNull(minutes, "传入的参数不能为空");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minutes);
        return formatDate(calendar.getTime(), yyyy_MM_dd_hh_mm_ss);
    }

    /**
     * 小时的加减
     *
     * @param hour hour
     * @return return
     */
    public static String addHour(Integer hour) {
        Assert.notNull(hour, "传入的参数不能为空");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return formatDate(calendar.getTime(), yyyy_MM_dd_hh_mm_ss);
        //return DateFormatUtils.format(System.currentTimeMillis() + hour * 60 * 60 * 1000, DateUtils.yyyy_MM_dd_hh_mm_ss);
    }

    /**
     * 天数的加减
     *
     * @param day day
     * @return String
     */
    public static String addDay(Integer day) {
        Assert.notNull(day, "传入的参数不能为空");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return formatDate(calendar.getTime(), yyyy_MM_dd_hh_mm_ss);
        //return DateFormatUtils.format(System.currentTimeMillis() + day * 24 * 60 * 60 * 1000, DateUtils.yyyy_MM_dd_hh_mm_ss);
    }

    /**
     * 天数的加减
     *
     * @param month month
     * @return String
     */
    public static String addMon(Integer month) {
        Assert.notNull(month, "传入的参数不能为空");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return formatDate(calendar.getTime(), yyyy_MM_dd_hh_mm_ss);
    }


    public static void main(String[] args) {
        Date endOfDay = getEndOfDay(new Date());
        Date startOfDay = getStartOfDay(new Date());
        logger.info("start date:[{}]", DateUtils.formatDate(startOfDay, DateUtils.yyyy_MM_dd_hh_mm_ss));
        logger.info("end date:[{}]", DateUtils.formatDate(endOfDay, DateUtils.yyyy_MM_dd_hh_mm_ss));
        logger.info("commons 3 - long -string currday :【{}】", longToStringDate(System.currentTimeMillis()));
        logger.info("addSecond :【{}】", addSecond(1));
        logger.info("addSecond:【{}】", addSecond(-1));
        logger.info("addMinute:【{}】", addMinute(-1));
        logger.info("addMinute:【{}】", addMinute(1));
        logger.info(" addHour :【{}】", addHour(-1));
        logger.info(" addHour :【{}】", addHour(1));
        logger.info(" addDay :【{}】", addDay(-15));
        logger.info("  addDay:【{}】", addDay(17));
        logger.info("  addMon:【{}】", addMon(1));
        logger.info("  addMon:【{}】", addMon(-1));
        logger.info("calc:[{}]",Calendar.getInstance().toString());
    }
}
