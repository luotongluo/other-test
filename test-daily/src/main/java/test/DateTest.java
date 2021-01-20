package test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Random;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * @author tong.luo
 * @description DateTest
 * @date 2021/1/18 16:00
 */
public class DateTest {
    public static void main(String[] args) {

        new DateTest().useDate();
        new DateTest().testDate();
        new DateTest().testTime1();
        new DateTest().testDurat();
    }

    void testDurat() {
        Duration duration = Duration.parse("P1DT1H1M1S");
        print("当前时间加上1天1小时1分钟1秒的差值: ", duration.getSeconds());

        Duration duration1 = Duration.parse("P2D");
        print("当前时间加上2天的差值: ", duration1.getSeconds());

        Duration duration2 = Duration.parse("PT2H");
        print("当前时间加上2小时的差值: ", duration2.getSeconds());

        Duration duration3 = Duration.parse("PT-2H");
        print("当前时间减去2小时的差值: ", duration3.getSeconds());

        Duration duration4 = Duration.parse("PT-2H30M");
        print("当前时间减去1小30分的差值: ", duration4.getSeconds());

        Duration duration5 = Duration.parse("PT-2H-30M");
        print("当前时间减去2小30分的差值: ", duration5.getSeconds());

        // 上面的也可以写成这样
        Duration duration6 = Duration.parse("-PT2H30M");
        print("当前时间减去2小30分的差值: ", duration6.getSeconds());

    }

    void testTime1() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);

        // 根据两个时间获取 Duration
        Duration duration = Duration.between(today, tomorrow);
        print("获取纳秒数差值：", duration.toNanos());
        print("获取毫秒数差值：", duration.toMillis());
        print("获取秒数差值: ", duration.getSeconds());
        print("获取分钟数差值：", duration.toMinutes());
        print("获取小时数差值：", duration.toHours());
        print("获取天数差值：", duration.toDays());

        // 当第1个时间比第2个时间小时为false, 反之true。可以用来判断2个时间的大小。
        boolean negative = duration.isNegative();
        print("isNegative: ", negative);

        // 以1天的差值创建Duration
        Duration duration1 = Duration.ofDays(1);
        print("以1天的差值创建Duration: ", duration1.getSeconds());
    }

    void testDate() {
        System.out.println("LocalDateTime: " + LocalDateTime.now());
        System.out.println("LocalDate: " + LocalDate.now());
        System.out.println("LocalTime: " + LocalTime.now());
        System.out.println("Instant: (UTC+0)" + Instant.now());
        System.out.println("Instant: (UTC+8)" + Instant.now().atZone(ZoneId.systemDefault()));
    }

    void useDate() {
        // 获取当天日期时间
        LocalDate today = LocalDate.now();
        print("获取当天日期时间: ", today);

        // 加一天
        LocalDate tomorrow = today.plusDays(1);
        print("加一天: ", tomorrow);

        // 加一个月
        LocalDate nextMonth = today.plusMonths(1);
        print("加一个月: ", nextMonth);

        // 减一天
        LocalDate yesterday = today.minusDays(1);
        print("减一天: ", yesterday);

        // 减一个月
        LocalDate lastMonth = today.minusMonths(1);
        print("减一个月: ", lastMonth);

        // 获取今天是本月第几天
        int dayOfMonth = today.getDayOfMonth();
        print("获取今天是本月第几天: ", dayOfMonth);

        // 获取今天是本周第几天
        int dayOfWeek = today.getDayOfWeek().getValue();
        print("获取今天是本周第几天: ", dayOfWeek);

        // 获取今天是本年第几天
        int dayOfYear = today.getDayOfYear();
        print("获取今天是本年第几天: ", dayOfYear);

        // 获取本月天数。
        int daysOfMonth = today.lengthOfMonth();
        print("获取本月天数: ", daysOfMonth);

        // 获取本年天数
        int daysOfYear = today.lengthOfYear();
        print("获取本年天数: ", daysOfYear);

        // 获取本月指定的第n天
        LocalDate date1 = today.withDayOfMonth(15);
        print("获取本月指定的第n天: ", date1);

        // 获取本月的最后一天
        LocalDate lastDaysOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        print("获取本月的最后一天: ", lastDaysOfMonth);

        // 日期字符串解析。 严格按照ISO yyyy-MM-dd 验证
        LocalDate date = LocalDate.parse("2021-01-17");
        print("日期字符串解析: ", date);

        // 日期字符串解析。 自定义格式
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-M-dd");
        LocalDate date2 = LocalDate.parse("2021-1-17", dft);
        print("日期字符串解析(日期字符串解析): ", date2);

        // 格式化日期
        String dateStr = today.format(dft);
        print("格式化日期: ", dateStr);

        // 自定义日期
        LocalDate cusDate = LocalDate.of(2020, 8, 14);
        print("自定义日期: ", cusDate);

        // 日期比较
        boolean before = today.isBefore(tomorrow);
        print("今天是否比明天早: ", before);

        boolean before1 = today.isBefore(yesterday);
        print("今天是否比昨天早: ", before1);

        boolean after = today.isAfter(tomorrow);
        print("今天是否比明天晚: ", after);

        boolean after1 = today.isAfter(yesterday);
        print("今天是否比昨天晚: ", after1);

        // 获取两个时间相差多少天/周/月...  根据单位不同返回不同
        long until = today.until(nextMonth, ChronoUnit.WEEKS);
        print("今天到下个月相差几周: ", until);

        Month month = today.getMonth();
        print("月份：", month);
        print("月份: ", month.getValue());

    }

    private static void print(Object object1, Object object) {
        System.out.println(object1 + ":" + object);
    }
}
