package com.lt.dailytest.utils.common;

import java.util.Collection;

/**
 * @author tong.luo
 * @description CollectionUtils
 * @date 2021/12/6 10:46
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection collection) {
        return org.springframework.util.CollectionUtils.isEmpty(collection);
    }
}
