package com.lt.dailytest.lombok;

/**
 * @author tong.luo
 * @description TestLambda
 * @date 2021/8/12 17:14
 */
public class TestLambda {
    public void operator(Long num1, Long num2, MyFunc<Long, Long> myFunc) {
        System.out.println(myFunc.getValue(num1, num2));
    }


}
