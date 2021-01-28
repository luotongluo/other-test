package com.lt.springcloudtest.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tong.luo
 * @description BaseMap
 * {"header:{},"body":{}"}
 * @date 2021/1/27 16:39
 */
public class BaseMap {
    private static final String succCode = "200";
    private static final String succCodeMsg = "请求成功";
    private static final String errCodeMsg = "请求成功";
    private static final String errCode = "9999";

    private static final String header = "header";
    private static final String body = "body";
    private static final String code = "code";
    private static final String codeMsg = "codeMsg";
    public static final String data = "data";


    private static HashMap<Object, Object> hashMap;

    public static Map getBaseMapOfSuccess() {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put(code, succCode);
        hashMap.put(codeMsg, succCodeMsg);
        return hashMap;
    }

    public static Map getBaseMapOfError() {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put(code, errCode);
        hashMap.put(codeMsg, errCodeMsg);
        return hashMap;
    }
}
