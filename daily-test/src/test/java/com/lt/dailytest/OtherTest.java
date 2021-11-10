package com.lt.dailytest;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.dao.StockTableMapper;
import com.lt.dailytest.dao.TestMapper;
import com.lt.dailytest.entity.StockTable;
import com.lt.dailytest.service.impl.TableServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tong.luo
 * @description OtherTest
 * @date 2021/4/23 17:40
 */
@SpringBootTest
public class OtherTest {
    @Autowired
    TableServiceImpl tableService;
    @Autowired
    TestMapper testMapper;
    @Autowired
    StockTableMapper stockTableDao;

    public static void main(String[] args) {
//        String val = null;
//        BigDecimal bigDecimal = new BigDecimal(val);
        String val = "FXC5310XXY-4Y-D339-8501010/60（2103-039）";

        extractedRevoseStr(val);

        Thread thread = new Thread();


        String replaceAll = val.replaceAll("/", "\\\\");
        System.out.println(replaceAll);

    }

    private static void extractedRevoseStr(String val) {
        ArrayList<Object> objects = new ArrayList<>(val.length());
        for (int i = 0; i < val.length(); i++) {
            objects.add(i, val.charAt(val.length() - i - 1));
        }
        System.out.println(JSON.toJSONString(objects));
        StringBuffer stringBuffer = new StringBuffer();
        objects.forEach(a -> {
            stringBuffer.append(a);
        });
        System.out.println(JSON.toJSONString(stringBuffer));
    }


    @Test
    public void test() {
        this.tableService.testSql();
    }

    @Test
    public void testMapper() {
        List<Object> objectList = this.testMapper.selectAll();
        System.out.println(JSON.toJSONString(objectList));
        List<StockTable> stockTables = this.stockTableDao.queryAll(new StockTable());
        System.out.println(JSON.toJSONString(stockTables));
    }

    @Test
    public void timeTest() {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = LocalTime.now();
        System.out.println("localDate :" + localDate);
        System.out.println("localDateTime :" + localDateTime);
        System.out.println("localTime :" + localTime);
    }
}
