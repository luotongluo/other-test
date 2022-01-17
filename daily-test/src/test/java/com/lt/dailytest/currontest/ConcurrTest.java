package com.lt.dailytest.currontest;

import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author tong.luo
 * @description ConcurrTest
 * @date 2021/8/5 15:08
 */
public class ConcurrTest {
    private static final long count = 10000l;
    @Test
    public void concurrTest(){
        LinkedTransferQueue<Object> objects = new LinkedTransferQueue<>();
    }
}
