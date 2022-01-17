package com.lt.dailytest.controller;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.entity.StockMainTable;
import com.lt.dailytest.service.StockTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * @author tong.luo
 * @description StockController
 * @date 2021/11/18 15:09
 */
@RestController
@RequestMapping("stock")
public class StockController {
    @Autowired
    private StockTableService stockTableService;

    @RequestMapping(value = {"stockIndex"})
    public ModelAndView getIndexPage(String req) {
        ModelAndView modelAndView = new ModelAndView("stock/stockIndex");

        List<StockMainTable> data = this.stockTableService.getData();
        modelAndView.addObject("stockdata", data);
        System.out.println("data :" + JSON.toJSONString(modelAndView));
        return modelAndView;
    }

}
