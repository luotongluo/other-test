package com.lt.dailytest.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tong.luo
 * @description ThreadPoolUtils
 * @date 2021/6/30 11:19
 */
public class ThreadPoolUtils extends ThreadPoolExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolUtils.class);
    private String poolName;

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public ThreadPoolUtils(int poolSize, String poolName) {
        super(poolSize, poolSize, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new NamingThreadFactory(poolName));
        this.poolName = poolName;
    }


    private static class NamingThreadFactory implements ThreadFactory {
        private String threadName;
        private AtomicInteger counter = new AtomicInteger(1);

        public NamingThreadFactory(String poolName) {
            this.threadName = poolName;
        }

        @Override
        public Thread newThread(Runnable r) {
            int andIncrement = this.counter.getAndIncrement();

            return new Thread(r, threadName + "-" + andIncrement);
        }

    }

    @Override
    public String toString() {
        String str = super.toString();
        int idx = str.indexOf("[");
        if (idx == -1) {
            return "[name = " + poolName + "]";
        }
        String s = str.substring(idx + 1);
        return "[name = " + poolName + ", " + s;
    }
}
