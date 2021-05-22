package com.lt.dailytest.controller;

import com.lt.dailytest.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tong.luo
 * @description AsyncController
 * @date 2021/5/18 21:19
 */
@RestController("async")
public class AsyncController {
    @Autowired
    AsyncService asyncService;

    @RequestMapping("testasyc")
    public void testasyc() {
        this.asyncService.executeAsync();
    }
}
