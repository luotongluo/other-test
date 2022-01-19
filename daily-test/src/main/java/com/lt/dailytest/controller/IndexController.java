package com.lt.dailytest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/**
 * @author tong.luo
 * @description IndexController
 * @date 2021/3/16 16:39
 */
@RestController
@RequestMapping("index")
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = {"", "/", "\\"})
    public ModelAndView getIndexPage(String req) {
        ModelAndView modelAndView = new ModelAndView("index/index");
        modelAndView.addObject("hello", "hello");
        modelAndView.addObject("hello2", "hello2");
        modelAndView.addObject("users", Arrays.asList("张三", "李四", "王五"));
        modelAndView.addObject("students", Arrays.asList("王琴", "陈军", "郭大伟", "陈明"));
        return modelAndView;
    }

    @RequestMapping(value = {"login"})
    public String login(String req) {
        return "login sucess!!!";
    }

    @RequestMapping(value = {"logPrient"})
    public String logPrient(String req) {
        LOGGER.info("info:::[{}]", "com.lt.dailytest.controller.IndexController.logPrien");
        LOGGER.warn("warn:::[{}]", "com.lt.dailytest.controller.IndexController.logPrien");
        LOGGER.debug("debug:::[{}]", "com.lt.dailytest.controller.IndexController.logPrien");
        LOGGER.error("error:::[{}]", "com.lt.dailytest.controller.IndexController.logPrien");
        LOGGER.trace("trace:::[{}]", "com.lt.dailytest.controller.IndexController.logPrien");
        return "logPrient!!!";
    }
}
