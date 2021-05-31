package com.lt.dailytest;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.bean.CopyBean1;
import com.lt.dailytest.bean.CopyBean2;
import net.sf.cglib.beans.BeanCopier;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.Date;

/**
 * @author tong.luo
 * @description CopyBeanTest
 * @date 2021/5/31 19:12
 */
public class CopyBeanTest {
    private static final Logger logger = LoggerFactory.getLogger(CopyBeanTest.class);

    public static void main(String[] args) {

        cglibTest();
        springTest();
        dozerTest();
    }


    public static void cglibTest() {
        CopyBean1 copyBean1 = new CopyBean1();
        copyBean1.setName("123");
        copyBean1.setAge(012);
        copyBean1.setAnInt(022);
        copyBean1.setDate(new Date());
        copyBean1.setStringList(Arrays.asList("123", "2222"));
        CopyBean2 copyBean2 = new CopyBean2();
        BeanCopier beanCopier = BeanCopier.create(copyBean1.getClass(), copyBean2.getClass(), false);
        beanCopier.copy(copyBean1,copyBean2,null);
        copyBean2.setStringList(Arrays.asList("222","222"));
        logger.info("cglibTest:copyBean1:[{}]", JSON.toJSONString(copyBean1));
        logger.info("cglibTest:copyBean2:[{}]", JSON.toJSONString(copyBean2));
    }
    public static void dozerTest() {
        CopyBean1 copyBean1 = new CopyBean1();
        copyBean1.setName("123");
        copyBean1.setAge(012);
        copyBean1.setAnInt(022);
        copyBean1.setDate(new Date());
        copyBean1.setStringList(Arrays.asList("123", "2222"));
        CopyBean2 copyBean2 = new CopyBean2();
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.map(copyBean1,copyBean2);
        copyBean2.setStringList(Arrays.asList("222","222"));
        logger.info("dozerTest:copyBean1:[{}]", JSON.toJSONString(copyBean1));
        logger.info("dozerTest:copyBean2:[{}]", JSON.toJSONString(copyBean2));
    }
    public static void springTest() {
        CopyBean1 copyBean1 = new CopyBean1();
        copyBean1.setName("123");
        copyBean1.setAge(012);
        copyBean1.setAnInt(022);
        copyBean1.setDate(new Date());
        copyBean1.setStringList(Arrays.asList("123", "2222"));
        CopyBean2 copyBean2 = new CopyBean2();
        BeanUtils.copyProperties(copyBean1,copyBean2);
        copyBean2.setStringList(Arrays.asList("222","222"));
        logger.info("springTest:copyBean1:[{}]", JSON.toJSONString(copyBean1));
        logger.info("springTest:copyBean2:[{}]", JSON.toJSONString(copyBean2));
    }
}
