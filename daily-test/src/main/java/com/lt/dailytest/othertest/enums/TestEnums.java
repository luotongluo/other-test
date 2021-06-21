package com.lt.dailytest.othertest.enums;

import com.alibaba.fastjson.JSON;

/**
 * @author tong.luo
 * @description TestEnums
 * @date 2021/6/8 16:33
 */
public enum TestEnums {
    TEST1,
    TEST2,TEST3,
    TEST4,TEST5,
    TEST6,
    ;

    public static void main(String[] args) {
        String name = TestEnums.TEST1.name();
        TestEnums[] values = TestEnums.values();
        System.out.println(name);
        System.out.println(JSON.toJSONString(values));
    }
}
