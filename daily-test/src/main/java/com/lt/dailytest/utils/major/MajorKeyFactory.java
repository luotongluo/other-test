package com.lt.dailytest.utils.major;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author tong.luo
 * @description MajorKeyFactory
 * @date 2021/7/16 17:45
 */
@Component
public class MajorKeyFactory implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static String generatePrimaryKey() {
        String majorKey = ((IMajorKey) (context.getBean("primaryKey"))).getMajorKey();
        return majorKey;
    }

    /**
     * 生成主键
     *
     * @param
     * @return Long 返回类型
     * @title generatePrimaryKeyLongVal
     * @description
     */
    public static Long generatePrimaryKeyLongVal() {
        String majorKey = ((IMajorKey) (context.getBean("primaryKey"))).getMajorKey();
        return Long.parseLong(majorKey);
    }
}
