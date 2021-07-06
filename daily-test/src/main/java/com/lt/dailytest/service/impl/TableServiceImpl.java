package com.lt.dailytest.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.dao.TestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tong.luo
 * @description TableServiceImpl
 * @date 2021/7/6 11:03
 */
@Service
public class TableServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(TableServiceImpl.class);
    @Autowired
    TestMapper testMapper;

    public void testSql(){
        List<Object> objects = this.testMapper.selectAll();
        logger.info("testSql-->[{}]", JSON.toJSONString(objects));
    }
}
