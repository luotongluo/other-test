package com.lt.dailytest.othertest.current;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author tong.luo
 * @description SelfReadWriteLock　
 * @date 2021/11/5 10:28
 */
public class SelfReadWriteLock {
    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    // 获取一个key对应的value
    public static final Object get(String key) {
        r.lock();
        try {
            //为了演示出效果，get操作再细分如下，开始—>结束视为原子步骤
            System.out.println("正在做读的操作,key:" + key + " 开始");
            Thread.sleep(10);
            Object object = map.get(key);
            System.out.println("正在做读的操作,key:" + key + ",value:" + object + " 结束");
            System.out.println();
            return object;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            r.unlock();
        }
        return key;
    }

    // 设置key对应的value，并返回旧有的value
    public static final Object put(String key, Object value) {
        w.lock();
        try {
            //为了演示效果，set操作再细分如下，开始—>结束整个过程视为原子步骤
            System.out.println("正在做写的操作,key:" + key + ",value:" + value + "开始.");
            Thread.sleep(10);
            Object object = map.put(key, value);
            System.out.println("正在做写的操作,key:" + key + ",value:" + value + "结束.");
            System.out.println();
            return object;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.unlock();
        }
        return value;
    }

    // 清空所有的内容
    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        int threadpool = 300;
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        threadPool.execute(() -> {
            for (int i = 0; i < threadpool; i++) {
                SelfReadWriteLock.put(i + "", i + "");
            }
        });
        threadPool.execute(() -> {
            for (int i = 0; i < threadpool; i++) {
                SelfReadWriteLock.get(i + "");
            }
        });
        threadPool.shutdown();
        System.out.println("waiting...");
        long end = System.currentTimeMillis();
        System.out.println("Lock cost:" + (end - start));
    }
}

