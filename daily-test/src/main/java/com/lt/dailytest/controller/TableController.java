package com.lt.dailytest.controller;

import com.lt.dailytest.entity.StockTable;
import com.lt.dailytest.service.StockTableService;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 * @author tong.luo
 * @description TableController
 * @date 2021/3/16 17:33
 */
@RequestMapping("table")
@RestController
public class TableController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableController.class);
    @Autowired
    private StockTableService stockTableService;

    @RequestMapping("tableIndex")
    public ModelAndView getTableIndex() {
        String pathname = "table/index";
//        ModelAndView modelAndView = new ModelAndView(pathname);
        return new ModelAndView(pathname);
    }

    /**
     * 将表中的现有数据进行更新 定时
     */
    @RequestMapping("synAllData")
    public void synAllData() {
        this.stockTableService.synAllData();
    }

    /**
     * 将表中的现有数据进行更新
     * 并且交易日期在当天的0点之后的数据
     */
    @RequestMapping("synAllDataOnce")
    public void synAllDataOnce(HttpServletResponse httpResponse) {
        this.stockTableService.synAllDataOnce();
    }

    /**
     * 将表中的数据进行初始化
     */
    @RequestMapping("initConcurrDayData")
    public void initConcurrDayData() {
        LOGGER.info("init -->initConcurrDayData:" + LocalDate.now().toString());
        this.stockTableService.initAllData();
        LOGGER.info("done -->initConcurrDayData：" + LocalDate.now().toString());
    }

}
