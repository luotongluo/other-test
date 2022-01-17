package com.lt.dailytest.other;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author tong.luo
 * @description CalcuteTest
 * @date 2021/12/9 15:40
 */
public class CalcuteTest {
    @Test
    public void caucuTest() {
        BigDecimal divide = new BigDecimal("2.58").divide(new BigDecimal("511.52"), 10, BigDecimal.ROUND_DOWN);
        System.out.println(divide);
        BigDecimal add = new BigDecimal("3501749648.32").add(new BigDecimal("2387815.71"));
        System.out.println(add);
    }
    @Test
    public void dateTest() {
        String invoiceYearSeason = "20213";
        String substring = invoiceYearSeason.substring(invoiceYearSeason.length() - 1, invoiceYearSeason.length());
        String substring2 = invoiceYearSeason.substring(0, invoiceYearSeason.length() -1 );
        System.out.println(substring);
        System.out.println(substring2);
    }
}
