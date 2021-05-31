package com.lt.dailytest.othertest.current;

import java.util.concurrent.Semaphore;

/**
 * @author tong.luo
 * @description PoolTest
 * @date 2021/3/15 14:15
 */
public class SemaphorePoolTest {
    private static final int MAX_AVAILABLE = 100;
    //最大可以有100个 公平的池
    private final Semaphore semaphore = new Semaphore(MAX_AVAILABLE, true);

    public Object getItem() throws Exception {
        //申请一个许可
        semaphore.acquire();

        return getNextAvailablItem();
    }

    public void putItem(Object obj) {
        if (markAsUnused(obj)) {
            //新增了一个可用项，释放了一个许可
            semaphore.release();
        }
    }

    //存放对象的复用对象
    protected Object[] items = new Object[MAX_AVAILABLE];
    //用于标记池中的项是否正在被使用
    protected boolean[] used = new boolean[MAX_AVAILABLE];

    /**
     * 标记为未使用
     *
     * @param obj
     * @return
     */
    private synchronized boolean markAsUnused(Object obj) {
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            if (obj == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private Object getNextAvailablItem() {
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null;

    }

    public static void main(String[] args) throws Exception {
        SemaphorePoolTest semaphorePoolTest = new SemaphorePoolTest();
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            Object item = semaphorePoolTest.getItem();
            semaphorePoolTest.putItem(i);
            System.out.println(item);
        }
    }
}
