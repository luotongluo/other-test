package com.lt.dailytest.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tong.luo
 * @description JsonUtils
 * Json实体类
 * @date 2021/8/11 15:31
 */
public class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    /**
     * 将obj转换为string 返回
     * 为空则返回空串
     *
     * @param object
     * @return
     */
    public static String getString(Object object) {
        if (null == object) {
            return "";
        }
        return JSON.toJSONString(object);
    }
}
