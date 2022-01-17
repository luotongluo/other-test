package com.lt.dailytest.othertest.current;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author tong.luo
 * @description BlockTest
 * @date 2021/11/4 15:11
 */
public class BlockTest {
    public BlockTest() {
        //用数组实现的环形队列，在构造参数中 要求传入数组的容量
        BlockingQueue bl = new ArrayBlockingQueue(12);
        new LinkedBlockingDeque<Object>();

        new DelayQueue<>();
    }
}
