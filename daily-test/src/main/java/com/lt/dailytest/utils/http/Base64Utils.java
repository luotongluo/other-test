package com.lt.dailytest.utils.http;


/**
 * @author tong.luo
 * @description Base64Utils
 * @date 2021/4/9 16:27
 */
public class Base64Utils {


    public static String base65Encode(String val) {

        return org.springframework.util.Base64Utils.encodeToString(val.getBytes());
    }
    public static String base65Decode(String val,String utf) {

        return new String(org.springframework.util.Base64Utils.decodeFromString(val));
    }

    public static void main(String[] args) {
        String val = "123奥术大师多asdasd！@#￥%……&&*";
        String s = base65Decode(base65Encode(val), null);
        System.out.println(s);
    }
}
