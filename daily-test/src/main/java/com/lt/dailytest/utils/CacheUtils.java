package com.lt.dailytest.utils;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.othertest.validate.TestBean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tong.luo
 * @description CacheUtils
 * @date 2021/7/7 13:54
 */
public class CacheUtils {
    /**
     * 进行元素存储的map
     */
    private static ConcurrentHashMap hashMap;
    //private static HashSet keySet;
    /**
     * key集合
     */
    private static List keyList;
    private static final Integer capacity = 24;
    private static final Lock lock = new ReentrantLock();

    static {
        hashMap = new ConcurrentHashMap<>(BigDecimal.valueOf(capacity).multiply(new BigDecimal("1.75")).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
        //keySet = new HashSet<>(capacity);
        keyList = new CopyOnWriteArrayList();
    }

    /**
     * 私有化无参构造
     */
    private CacheUtils() {
    }

    /**
     * 添加缓存
     *
     * @param key
     * @param t
     */
    public static void addCache(String key, Object t) {
        try {
            lock.lock();
            Object o = hashMap.get(key);
            if (null == o && keyList.size() < capacity) {
                //集合中不重复的元素进行添加
                addCacheMap(key, t);
            } else if (null == o && keyList.size() > capacity) {
                //移除新添加的第一位元素，后面的元素放在最后一位
                removeKey();

                addCacheMap(key, t);
            } else {
                //hashmap中已经存在该key  是更新还是ignore
            }
        } finally {
            lock.unlock();
        }

    }

    private static void removeKey() {
        try {
            lock.lock();
            Object keyRemove = keyList.get(0);
            keyList.remove(keyRemove);
            hashMap.remove(String.valueOf(keyRemove));
        } finally {
            lock.unlock();
        }

    }

    private static void addCacheMap(String key, Object t) {
        try {
            lock.lock();
            hashMap.put(key, t);
            keyList.add(key);
        } finally {
            lock.unlock();
        }

    }

    public static Object getCache(String key) {
        try {
            lock.lock();
            return hashMap.get(key);
        } finally {
            lock.unlock();
        }

    }

    public static Map getCacheMap() {
        return hashMap;
    }

    public static void main(String[] args) throws Exception {
        TestBean testBean = new TestBean();
        testBean.setEmail("123123");
        hashMap.put("test", testBean);
        TestBean test = (TestBean) hashMap.get("test");
        System.out.println(JSON.toJSONString(test));

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        threadPoolExecutor.execute(() -> {
            for (int i = 0; i < 10000; i++) {
                UUID uuid = UUID.randomUUID();
                CacheUtils.addCache(System.currentTimeMillis() + "::" + i, new TestBean());
            }
        });
        TimeUnit.NANOSECONDS.sleep(5000);
        //threadPoolExecutor.awaitTermination(100,TimeUnit.NANOSECONDS);
        /*int activeCount = threadPoolExecutor.getActiveCount();
        System.out.println(JSON.toJSONString(activeCount));*/
        //TimeUnit.SECONDS.sleep(2);
        System.out.println("size:" + CacheUtils.hashMap.size() + " \n content:" + CacheUtils.getCacheMap().toString());
        threadPoolExecutor.shutdown();
    }
}

class Test {
    public static void main(String[] args) {
        List useList = new CopyOnWriteArrayList();
        for (int i = 0; i < 10000; i++) {
            CacheUtils.addCache(System.currentTimeMillis() + "::" + i, i);
        }
        System.out.println(JSON.toJSONString(CacheUtils.getCacheMap()));
        System.out.println(JSON.toJSONString(CacheUtils.getCacheMap().size()));
        /*useList.remove(useList.get(0));
        System.out.println(JSON.toJSONString(useList));
        useList.add("124");
        System.out.println(JSON.toJSONString(useList));*/
    }
}
