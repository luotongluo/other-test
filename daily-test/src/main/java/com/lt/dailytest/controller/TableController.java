package com.lt.dailytest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author tong.luo
 * @description TableController
 * @date 2021/3/16 17:33
 */
@RequestMapping("table")
@RestController
public class TableController {
    @RequestMapping("tableindex")
    public ModelAndView getTableIndex(){
        String pathname = "table/index";
        ModelAndView modelAndView = new ModelAndView(pathname);
        return modelAndView;
    }
}
