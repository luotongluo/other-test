package com.lt.dailytest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Map;

/**
 * @author tong.luo
 * @description IndexController
 * @date 2021/3/16 16:39
 */
@RestController
public class IndexController {

    @RequestMapping(value = {"","/","\\"})
    public ModelAndView getIndexPage(String req){
        ModelAndView modelAndView = new ModelAndView("index/index");
        modelAndView.addObject("hello","hello");
        modelAndView.addObject("hello2","hello2");
        modelAndView.addObject("users", Arrays.asList("张三","李四","王五"));
        modelAndView.addObject("students", Arrays.asList("王琴","陈军","郭大伟","陈明"));
        return modelAndView;
    }
}
