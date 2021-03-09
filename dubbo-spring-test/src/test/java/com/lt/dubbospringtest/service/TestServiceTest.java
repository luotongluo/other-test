package com.lt.dubbospringtest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tong.luo
 * @description TestServiceTest
 * @date 2021/2/26 10:28
 */
@SpringBootTest
public class TestServiceTest {
    @Autowired
    private TestService testService;
    @Test
    public void test(){
        this.testService.test();
    }

    @Test
    public void testDubboRef(){
        this.testService.testDubboRef();
    }
}