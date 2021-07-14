package com.lt.dailytest.controller;

import com.lt.dailytest.entity.StockTable;
import com.lt.dailytest.service.StockTableService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private StockTableService stockTableService;

    @RequestMapping("tableIndex")
    public ModelAndView getTableIndex() {
        String pathname = "table/index";
//        ModelAndView modelAndView = new ModelAndView(pathname);
        return new ModelAndView(pathname);
    }

    @RequestMapping("synAllData")
    public void synAllData() {
        this.stockTableService.synAllData();
    }

}
