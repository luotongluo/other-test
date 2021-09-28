package com.lt.dailytest.controller;

import com.lt.dailytest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tong.luo
 * @description TestController
 * @date 2021/8/23 17:36
 */
@RestController
@RequestMapping("tset")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("getNum")
    public void getNum(TestService testService) {
        Object num = this.testService.getNum();
    }
}
