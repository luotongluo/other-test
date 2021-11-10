package com.lt.dailytest.other;

import com.lt.dailytest.exception.BusinessException;
import com.lt.dailytest.utils.common.DateUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author tong.luo
 * @description TimeTest
 * @date 2021/11/1 14:37
 */
public class TimeTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTest.class);

    @Test
    public void timeTest() {
        String time = "2021-11-02";
        Date date = DateUtils.parseDate(time, DateUtils.yyyy_MM_dd);
        String s = DateUtils.formatDate(date, DateUtils.yyyyMMdd);
        System.out.println(s);
    }

    @Test
    public void dateTest() {
        String applyTimeEnd = "2022-11-02";
        String applyTimeBegin = "2021-12-02";
        //结束天数 +1 才行 如果查询当天的时候会出现这样的问题
        Date date = DateUtils.parseDate(applyTimeEnd, "yyyy-MM-dd");
        //判断开始时间和结束时间在1年的时间内
        long mut = date.getTime() - DateUtils.parseDate(applyTimeBegin, "yyyy-MM-dd").getTime();
        LOGGER.info("pool:{}", mut);
        if (mut > 365 * 24 * 60 * 60 * 1000) {
            throw new BusinessException("请求参数：申领时间的开始时间和结束时间相差不能大于365天");
        }
    }
}
