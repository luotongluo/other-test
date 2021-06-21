package com.lt.conbase.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.conbase.dao.TestMapper;
import com.lt.conbase.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tong.luo
 * @description TestServiceImpl
 * @date 2021/6/18 20:48
 */
@Service
public class TestServiceImpl implements TestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);
    @Resource
    TestMapper testMapper;

    @Override
    public void test(){
        Object o = this.testMapper.selectAll();
        LOGGER.info("req[{}]", JSON.toJSONString(o));
    }
}
