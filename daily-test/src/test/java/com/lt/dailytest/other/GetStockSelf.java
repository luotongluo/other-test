package com.lt.dailytest.other;

import com.lt.dailytest.dao.StockTableMapper;
import com.lt.dailytest.service.impl.StockTableServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tong.luo
 * @description GetStockSelf
 * https://blog.csdn.net/fatOwen/article/details/7683029?utm_term=java%E8%8E%B7%E5%8F%96%E8%82%A1%E7%A5%A8%E4%BB%A3%E7%A0%81&utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~sobaiduweb~default-4-7683029&spm=3001.4430
 * 获取所有的股票代码：https://blog.csdn.net/leijia_xing/article/details/81142872
 * @date 2021/7/12 17:33
 */
@SpringBootTest
public class GetStockSelf {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetStockSelf.class);
    @Autowired
    StockTableMapper stockTableDao;
    @Autowired
    private StockTableServiceImpl stockTableService;

    @Test
    public void testsaveAllData() {
        this.stockTableService.synAllData();
    }
    @Test
    public void testinitAllData() {
        this.stockTableService.initAllData();
    }

    @Test
    public void testaddOrUpdateOne() {
        String num = "603529";
        this.stockTableService.synOneData(num);
    }

    @Test
    public void testSplite() {
        long start = System.currentTimeMillis();

        this.stockTableService.splateCurrDayAllData();
        LOGGER.info("cost:[{}]", (System.currentTimeMillis() - start));
    }

}
