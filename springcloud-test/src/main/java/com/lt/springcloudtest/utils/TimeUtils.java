package com.lt.springcloudtest.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tong.luo
 * @description TimeUtils
 * @date 2021/1/27 10:53
 */
public class TimeUtils {
    private static SimpleDateFormat dateformater;
    public static final String FORMAT_DEFAULT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_yyyyMMddHHmm = "yyyyMMddHHmm";
    public static final String FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String FORMAT_yyyyMMdd = "yyyy-MM-dd";


    public TimeUtils() {
    }

    /**
     * 判断是否为时间
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        String patternParam = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern = Pattern.compile(patternParam);
        Matcher m = pattern.matcher(strDate);
        return m.matches();
    }

    public static String getStringOfFirstDayInMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String temp = sdf.format(date);
        String firstDayInMoth = "";
        firstDayInMoth = temp + "-01";
        return firstDayInMoth;
    }

    public static Date getDateOfFirstDayInMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String temp = sdf.format(date);
        String firstDayInMoth = "";
        firstDayInMoth = temp + "-01";
        return parseDate(firstDayInMoth);
    }

    public static Date parseDate(String strDate) {
        Date date = null;

        try {
            date = getDateFormater().parse(strDate);
        } catch (Exception var3) {
        }

        return date;
    }

    private static DateFormat getDateFormater() {
        if (dateformater == null) {
            dateformater = new SimpleDateFormat(FORMAT_DEFAULT_TIMESTAMP);
        }

        return dateformater;
    }

    public static Date parseDate(String date, String formatType) {
        SimpleDateFormat f = new SimpleDateFormat(formatType);

        Date innerDate;
        try {
            innerDate = f.parse(date);
        } catch (ParseException var5) {
            innerDate = new Date();
            var5.printStackTrace();
        }

        return innerDate;
    }

    public static String getCurrentFormatDate(String formatType) {
        if (StringUtils.isEmpty(formatType)) {
            formatType = FORMAT_DEFAULT_TIMESTAMP;
        }

        Locale locale = Locale.SIMPLIFIED_CHINESE;
        SimpleDateFormat dateStyle = new SimpleDateFormat(formatType, locale);
        return dateStyle.format(new Date());
    }

    public static String formatDateTime(Date dt, String formatType) {
        String newDate = "";
        if (dt != null) {
            Locale locale = Locale.CHINESE;
            SimpleDateFormat dateStyle = new SimpleDateFormat(formatType, locale);
            newDate = dateStyle.format(dt);
        }

        return newDate;
    }

    public static Date format(String strDate, String aFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(aFormat);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    public static int getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(13, 0);
        cal.setTime(date);
        return cal.get(7) - 1 == 0 ? 7 : cal.get(7) - 1;
    }

    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(13, 0);
        cal.setTime(date);
        return cal.get(2) + 1;
    }

    public static Date getYesterday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, -1);
        return cal.getTime();
    }

    public static String getDate(long time, String format) {
        Date d = new Date();
        d.setTime(time);
        DateFormat df = new SimpleDateFormat(format);
        return df.format(d);
    }

    public static Date getDate(long time) {
        Date d = new Date();
        d.setTime(time);
        return d;
    }

    public static long getTimeStampDate(String timeStamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DEFAULT_TIMESTAMP);
        String sd = sdf.format(new Date(Long.parseLong(timeStamp + "000")));
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date = null;

        try {
            date = df.parse(sd);
        } catch (ParseException var8) {
            var8.printStackTrace();
        }

        long s = date.getTime() / 1000L;
        return s;
    }

    public static String getDateTimeStamp(String timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DEFAULT_TIMESTAMP);
        String sd = sdf.format(new Date(Long.parseLong(timeStamp + "000")));
        return sd;
    }

    public static String getTimeMinuteAdd(Date date, int x) {
        long new_d = date.getTime() + (long) (x * 60 * 1000);
        return getDate(new_d, FORMAT_DEFAULT_TIMESTAMP);
    }

    public static Date addDate(int Interval) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(12, Interval);
        Date date = c.getTime();
        return date;
    }

    public static Date addYear(Date date, int year) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(1, year);
        return c.getTime();
    }

    public static Boolean isInTheTime(Date time, Date starttime, Date endtime) {
        if (time != null && starttime != null && endtime != null) {
            return time.compareTo(starttime) >= 0 && time.compareTo(endtime) <= 0 ? true : false;
        } else {
            return false;
        }
    }

    public static Date getNowStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(11, 0);
        todayStart.set(12, 0);
        todayStart.set(13, 0);
        todayStart.set(14, 0);
        return todayStart.getTime();
    }

    public static Date getNowEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(11, 23);
        todayEnd.set(12, 59);
        todayEnd.set(13, 59);
        todayEnd.set(14, 999);
        return todayEnd.getTime();
    }

    public static String getNowTime() {
        Calendar Cld = Calendar.getInstance();
        int YY = Cld.get(1);
        int MM = Cld.get(2) + 1;
        int DD = Cld.get(5);
        int HH = Cld.get(11);
        int mm = Cld.get(12);
        int SS = Cld.get(13);
        int MI = Cld.get(14);
        String valueOf = String.valueOf(YY);
        String MMString = String.valueOf(MM);
        String DDString = String.valueOf(DD);
        String HHString = String.valueOf(HH);
        String mmString = String.valueOf(mm);
        String SSString = String.valueOf(SS);
        String MIString = String.valueOf(MI);
        return valueOf + MMString + DDString + HHString + mmString + SSString + MIString;
    }

    public static Date getEndOfDay(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DEFAULT_TIMESTAMP);
        Date parseDate = simpleDateFormat.parse(date);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(parseDate.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getStartOfDay(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DEFAULT_TIMESTAMP);
        Date parseDate = simpleDateFormat.parse(date);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(parseDate.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static int subYearDate(Date startTime, Date endTime) {
        if (startTime != null && endTime != null) {
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            start.setTime(startTime);
            end.setTime(endTime);
            int year = 0;
            if (end.get(1) > start.get(1)) {
//                int year = end.get(1) - start.get(1);
                year = end.get(1) - start.get(1);
                if (end.get(2) + 1 >= start.get(2) + 1) {
                    return end.get(5) >= start.get(5) ? year : year - 1;
                } else {
                    return year - 1;
                }
            } else {
                return year;
            }
        } else {
            return 0;
        }
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static synchronized String getNo(int k) {
        return getUserDate(FORMAT_yyyyMMddHHmmss) + "-" + getRandom(k);
    }

    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static synchronized String getRandom(int i) {
        Random jjj = new Random();
        if (i == 0) {
            return "";
        } else {
            String jj = "";

            for (int k = 0; k < i; ++k) {
                jj = jj + jjj.nextInt(9);
            }

            return jj;
        }
    }

    public static void main(String[] args) throws Exception{
//        Date d1 = parseDate("2014-01-02 11:30:23");
//        Date d2 = parseDate("2004-01-02 11:30:24");
//        System.out.println(formatDateTime(addYear(d2, 1), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(formatDateTime(addYear(d2, -1), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(formatDateTime(addYear(d2, 0), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(subYearDate(d2, d1));
//
//        int week = getMonth(new Date());
//        System.out.println(week);

        String nowTime = getDate(System.currentTimeMillis(), TimeUtils.FORMAT_DEFAULT_TIMESTAMP);
        System.out.println(nowTime);

        for (int i = 0; i < 10000; i++) {
            String no = getNo(10);
            System.out.println(no);
            Thread.sleep(100);
        }

    }

}
