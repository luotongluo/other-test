package com.lt.dailytest.lombok;

import org.junit.jupiter.api.Test;

/**
 * @author tong.luo
 * @description LombokTest
 * @date 2021/8/12 17:18
 */
public class LombokTest {
    @Test
    public void lobTest1(){
        TestLambda testLambda = new TestLambda();
        testLambda.operator(100L,200L,(x,y) -> x + y);
    }
}
