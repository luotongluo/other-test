package com.lt.springcloudtest.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.gson.GsonBuilder;
import com.lt.springcloudtest.bean.TestBean;
import com.lt.springcloudtest.enums.TimeEnums;
import com.lt.springcloudtest.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tong.luo
 * @description TestServiceImpl
 * @date 2021/1/4 15:29
 */
@Service
public class TestServiceImpl implements TestService {
    private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    /**
     * test
     *
     * @param s
     */
    @Override
    public void testJson(String s) {
        logger.info("init ……");
        TestBean testBean = new TestBean("123", 22, "33", new Date());
        String jsonString = JSON.toJSONString(testBean);
        String jsonGson = new GsonBuilder().setDateFormat(TimeEnums.YYYYMMDDHHMMSS).create().toJson(testBean);
        StringUtils.isNotBlank(jsonGson);
        System.out.println(jsonGson);
        System.out.println(jsonString);
    }
}
