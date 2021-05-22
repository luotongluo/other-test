package com.lt.dailytest;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.utils.JedisUtils;
import com.lt.dailytest.utils.ValidatorUtil;
import com.lt.dailytest.validate.TestBean;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class DailyTestApplicationTests {
    @Autowired
    private JedisUtils jedisUtils;

    @Test
    void contextLoads() {
        TestBean testBean = new TestBean();
        Map<String, StringBuffer> validate = ValidatorUtil.validate(testBean);
        System.out.println(JSON.toJSON(validate));
    }

    @Test
    void jedisTest() {
        String ss = "DailyTestApplicationTests";
        this.jedisUtils.set(ss, UUID.randomUUID());
        Object o = this.jedisUtils.get(ss);
        System.out.println(JSON.toJSONString(o));
    }

    public static void main(String[] args) {
        Integer a = new Integer(1234);
        Integer b = new Integer(1234);
        Integer d = 1234;
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }

    @Test
    public void readFile() throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("开票单张限额.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(resourceAsStream);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
         row = sheet.getRow(1);
         row = sheet.getRow(2);
         row = sheet.getRow(3);

    }
}
