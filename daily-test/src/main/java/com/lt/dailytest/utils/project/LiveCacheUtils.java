package com.lt.dailytest.utils.project;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.TimeUnit;

/**
 * @author tong.luo
 * @description LiveCacheUtils
 * 缓存单个对象
 * @date 2021/7/6 11:30
 */
public class LiveCacheUtils<T> {
    /**
     * 缓存时间
     */
    private final int cacheMillis;
    /**
     * 缓存对象
     */
    private final T element;
    /**
     * 缓存对象创建时间
     */
    private final long createTime;
    private long updateTime;

    public LiveCacheUtils(int cacheMillis, T element) {
        this.cacheMillis = cacheMillis;
        this.element = element;
        this.createTime = System.currentTimeMillis();
        this.updateTime = System.currentTimeMillis();
    }

    // 获取缓存对象
    public T getElement() {
        long currentTime = System.currentTimeMillis();
        if (cacheMillis > 0 && currentTime - createTime > cacheMillis) {
            return null;
        } else {
            return element;
        }
    }

    // 获取缓存对象，忽略缓存时间有效性
    public T getElementIfNecessary() {
        return element;
    }

    public int getCacheMillis() {
        return cacheMillis;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public static void main(String[] args) throws Exception {
        int cacheMoment = 100;
        LiveCacheUtils<Object> objectLiveCacheUtils = new LiveCacheUtils<>(cacheMoment, "12312322");
        TimeUnit.NANOSECONDS.sleep(50);
        Object element = objectLiveCacheUtils.getElement();
        Object elementIfNecessary = objectLiveCacheUtils.getElementIfNecessary();
        System.out.println(JSON.toJSONString(element));
        System.out.println(JSON.toJSONString(elementIfNecessary));
    }
}
