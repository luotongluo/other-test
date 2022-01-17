package com.lt.dailytest.othertest.leecode.curr;

import java.time.LocalDate;

/**
 * @author 12828
 * @description DayofYear
 * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
 * <p>
 * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/day-of-the-year
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/12/21 22:28
 */
public class DayofYear {
    public int dayOfYear(String date) {
        date = "2019-01-09";
        String[] split = date.split("-");
        int amount[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //判断年份是否为闰年
        if ((Integer.parseInt(split[0]) % 4 == 0 && Integer.parseInt(split[0]) % 100 != 0)
                || Integer.parseInt(split[0]) % 400 == 0) {
            amount[1]++;
        }
        int day = Integer.parseInt(split[2]);
        //获取当前月份的前一个月份的天数，与当前的天数相加
        for (int i = 0; i < Integer.parseInt(split[1]) - 1; i++) {
            day += amount[i];
        }

        return day;
    }
    public int dayOfYear1(String date) {
        String[] dates = date.split("-");
        return LocalDate.of(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2])).getDayOfYear();
    }
}
