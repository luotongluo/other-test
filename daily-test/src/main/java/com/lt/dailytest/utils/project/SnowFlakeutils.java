package com.lt.dailytest.utils.project;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tong.luo
 * @description SnowFlake
 * 分布式id的生成策略
 * https://zhuanlan.zhihu.com/p/107939861
 * @date 2021/3/26 15:58
 */
public class SnowFlakeutils {
    //起始的时间戳
    private final static long START_TIMESTAMP = 1480166465631L;
    //序列号占用的位数
    private final static long SEQUENCE_BIT = 12;
    //机器表示占用的位数
    private final static long MACHINE_BIT = 5;
    //数据中心占用的位数
    private final static long DATA_CENTER_BIT = 5;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);
    /**
     * 每一部分向左的唯一
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;
    //数据中心
    private long dataCenterId;
    //机器标识
    private long machineId;
    //序列号
    private long sequence = 0L;
    //上一次时间戳
    private long lastTimeStamp = -1L;

    private long getNextMill() {
        long mill = getNewTimeStamp();
        while (mill <= lastTimeStamp) {
            mill = getNewTimeStamp();
        }
        return mill;
    }

    /**
     * 根据指定的数据中心ID和机器标志ID生成指定的序列号
     *
     * @param dataCenterId 数据中心ID
     * @param machineId    机器标志ID
     */
    public SnowFlakeutils(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("DtaCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0！");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("MachineId can't be greater than MAX_MACHINE_NUM or less than 0！");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    public synchronized long nextId() {
        long currTimeStamp = getNewTimeStamp();
        if (currTimeStamp < lastTimeStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
        if (currTimeStamp == lastTimeStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currTimeStamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }
        return (currTimeStamp - START_TIMESTAMP) << TIMESTAMP_LEFT //时间戳部分
                | dataCenterId << DATA_CENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;
    }

    private long getNewTimeStamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
       /* SnowFlakeutils snowFlakeutils = new SnowFlakeutils(2, 3);
        for (int i = 0; i < (1 << 4); i++) {
            //10进制
            System.out.println(snowFlakeutils.nextId() + "----" + i);
        }*/
        HashMap<Long, Long> hashMap = new HashMap<>(4096);
        Executor defaultThreadPool = ThreadPoolUtils.getDefaultThreadPool();
        SnowFlakeutils snowFlakeutils1 = new SnowFlakeutils(1024, 125);
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            defaultThreadPool.execute(() -> {
//                for (int j = 0; j < (1 << 4); j++) {
                //10进制
                ReentrantLock lock = new ReentrantLock();
                long nextId = 0L;
                try {
                    lock.lock();
                    Thread.sleep(10);
                    nextId = snowFlakeutils1.nextId();
                    if (hashMap.containsKey(nextId)) {
                        System.out.println("contons:id : " + nextId);
                    } else {
                        System.out.println(nextId + "----" + finalI);
                        hashMap.put(nextId, nextId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }


//                }
            });
        }
        while (true) {

        }

    }

}
