package com.lt.conbase.service.impl;

import com.lt.conbase.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tong.luo
 * @description TestServiceImplTest
 * @date 2021/6/18 20:54
 */
@SpringBootTest
public class TestServiceImplTest {
    @Autowired
    TestServiceImpl testService;

    @Test
    void test1() {
        this.testService.test();
    }
}