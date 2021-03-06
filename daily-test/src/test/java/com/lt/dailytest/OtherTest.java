package com.lt.dailytest;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.dao.TestMapper;
import com.lt.dailytest.service.impl.TableServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
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

    public static void main(String[] args) {
//        String val = null;
//        BigDecimal bigDecimal = new BigDecimal(val);
        String val = "FXC5310XXY-4Y-D339-8501010/60（2103-039）";
        System.out.println(val.length());
    }

    @Test
    public void test() {
        this.tableService.testSql();
    }

    @Test
    public void testMapper() {
        List<Object> objectList = this.testMapper.selectAll();
        System.out.println(JSON.toJSONString(objectList));
    }
}
