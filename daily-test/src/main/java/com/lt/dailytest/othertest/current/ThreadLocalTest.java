package com.lt.dailytest.othertest.current;

import java.util.Date;

/**
 * lo
 *
 * @author tong.luo
 * @description ThreadLocalTest
 * @date 2021/3/15 15:11
 */
public class ThreadLocalTest implements Runnable {
    public static final ThreadLocal<Date> THREAD_LOCAL = new ThreadLocal<>();
    private long time;

    public ThreadLocalTest(long time) {
        this.time = time;
    }

    protected int loopval = 5000;

    @Override
    public void run() {
        //在每个线程中新建
        Date date = new Date(time);
        for (int i = 0; i < loopval; i++) {
            THREAD_LOCAL.set(date);
            if (THREAD_LOCAL.get().getTime() != time) {
                System.out.println("id =" + time + "localVal = " + THREAD_LOCAL.get().getTime());
            }
        }
        THREAD_LOCAL.remove();
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5000; i++) {
            ThreadLocalTest threadLocalTest = new ThreadLocalTest(i);
            threadLocalTest.run();

        }

        Thread.sleep(1000);
    }
}
