package time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * @author tong.luo
 * @description TimeTest
 * https://juejin.cn/post/6921551750763642894?utm_source=gold_browser_extension
 * @date 2021/1/25 17:49
 */
public class TimeTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        OffsetDateTime now1 = OffsetDateTime.now();
        System.out.println(now1);
        ZonedDateTime dateTime = ZonedDateTime.now();
        System.out.println(dateTime);
        System.out.println("================================");

//        LocalDateTimetest();
//        LocalDateTimeTestDateCal();
        localDateTest();
    }

    private static void localDateTest() {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        System.out.println("格式化输出：" + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT).format(now));
        String datetime = "2021-1-25 18:00:00";
        System.out.println("解析后输出：" + LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US)));
    }

    private static void LocalDateTimeTestDateCal() {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        System.out.println("before:" + now);
        //加3天
        LocalDateTime after = now.plusDays(3);
        after = after.plusHours(-4);
        System.out.println("after:" + now);

        //计算时间差
        Period between = Period.between(now.toLocalDate(), after.toLocalDate());
        System.out.println("相差天数：" + between.getDays());

        Duration duration = Duration.between(now.toLocalTime(), after.toLocalTime());
        System.out.println("xiangcha hour:" + duration.toHours());
    }

    private static void LocalDateTimetest() {
        LocalDateTime min = LocalDateTime.MIN;
        LocalDateTime MAX = LocalDateTime.MAX;
        System.out.println("localDateTime:min:" + min);
        System.out.println("localDateTime:MAX:" + MAX);
        System.out.println(min.getYear() + "-" + min.getMonthValue() + "-" + min.getDayOfMonth());
        System.out.println(MAX.getYear() + "-" + MAX.getMonthValue() + "-" + MAX.getDayOfMonth());
        System.out.println("================================");
    }
}
