package com.lt.dailytest.other;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.exception.BusinessException;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tong.luo
 * @description NumberTest
 * @date 2021/7/19 14:05
 */
public class NumberTest {
    private static List<Integer> promaNumberList = new ArrayList(1024);

    static {
        promaNumberList.add(2);
        promaNumberList.add(3);
        promaNumberList.add(5);
        promaNumberList.add(7);
    }

    public static void main(String[] args) throws Exception {
        int promaNumber = 100000;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(),
                0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        threadPoolExecutor.execute(() -> {
            judgeNumIsPromaNumber(promaNumber);
        });

        //TimeUnit.SECONDS.sleep(30);
        int activeCount = threadPoolExecutor.getActiveCount();
        System.out.println("activeCount:" + activeCount);
        while (activeCount < 1) {
            activeCount = threadPoolExecutor.getActiveCount();
            System.out.println("activeCount:" + activeCount);
            threadPoolExecutor.shutdown();
        }
        threadPoolExecutor.awaitTermination(10 * 1000,TimeUnit.NANOSECONDS);

        getPromNumber();
    }

    private static void getPromNumber() {
        System.out.println(JSON.toJSONString(promaNumberList));
    }

    public static void judgeNumIsPromaNumber(int number) {
        if (number < 1) {
            throw new BusinessException("计算的质数范围不能小于1");
        }

        for (int i = 10; i < number; i++) {
            boolean isPromaNumber = false;
            for (Integer integer : promaNumberList) {
                if (i % integer == 0 && i > integer) {
                    isPromaNumber = true;
                }
            }
            if (!isPromaNumber) {
                if (!promaNumberList.contains(i)) {
                    promaNumberList.add(i);
                }
            }
        }
    }
}
