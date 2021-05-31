package com.lt.dailytest;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.bean.CopyBean1;
import com.lt.dailytest.bean.CopyBean2;
import com.lt.dailytest.utils.JedisUtils;
import com.lt.dailytest.utils.ValidatorUtil;
import com.lt.dailytest.othertest.validate.TestBean;
import com.lt.dailytest.vo.MailVo;
import com.lt.dailytest.vo.MailVotest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class DailyTestApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JedisUtils jedisUtils;

    @Test
    public void mapStructTest(){
//        ConverMapper mapper = Mappers.getMapper(ConverMapper.class);
//        CopyBean1 copyBean1 = new CopyBean1();
//        copyBean1.setName("123");
//        copyBean1.setAge(012);
//        copyBean1.setAnInt(022);
//        copyBean1.setDate(new Date());
//        copyBean1.setStringList(Arrays.asList("123","2222"));
//
//        CopyBean2 copyBean2 = mapper.convertBean(copyBean1);
//        logger.info("copyBean1:[{}]",JSON.toJSONString(copyBean1));
//        logger.info("copyBean2:[{}]",JSON.toJSONString(copyBean2));
    }
    @Test
    public void testcopy(){
        MailVo mailVo = new MailVo();
        mailVo.setPageIndex(123);
        mailVo.setPageSize(123);
        mailVo.setBcc("123");
        MailVotest mailVotest = new MailVotest();
        BeanUtils.copyProperties(mailVo,mailVotest);
        logger.info("info:[{}]",JSON.toJSONString(mailVotest));
    }
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
        String fileName = "templates/开票单张限额.xls";
        //InputStream resourceAsStream = this.getClass().getResourceAsStream(fileName);
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        XSSFWorkbook wb = new XSSFWorkbook(resourceAsStream);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
         row = sheet.getRow(1);
         row = sheet.getRow(2);
         row = sheet.getRow(3);
    }
}
