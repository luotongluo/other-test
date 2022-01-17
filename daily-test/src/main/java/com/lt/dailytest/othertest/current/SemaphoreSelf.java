package com.lt.dailytest.othertest.current;

import java.util.concurrent.Semaphore;

/**
 * @author tong.luo
 * @description Semaphore
 * @date 2021/11/3 17:33
 */
public class SemaphoreSelf {
    public SemaphoreSelf() throws Exception{
        Semaphore semaphore = new Semaphore(10);
        //获取一个信号量进行操作
        semaphore.acquire();
        //释放
        semaphore.release();
    }
}
