package com.lt.dailytest.utils.common;

import java.util.Collection;

/**
 * @author tong.luo
 * @description StringUtils
 * @date 2021/12/6 10:36
 */
public class StringUtils {

    public static String newString() {
        return "";
    }

    /**
     * 判断字符串是否为空 含有空格的时候认为是非空的
     * Checks if a CharSequence is empty ("") or null.
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(CharSequence string) {
        return org.apache.commons.lang3.StringUtils.isEmpty(string);
    }

    public static boolean isNotEmpty(CharSequence string) {
        return !isEmpty(string);
    }

    /**
     * 含有空格的字符串的时候认为是空的
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     *
     * @param string
     * @return
     */
    public static boolean isBlank(CharSequence string) {
        return org.apache.commons.lang3.StringUtils.isBlank(string);
    }

    public static boolean isNotBlank(CharSequence string) {
        return !isBlank(string);
    }
}
