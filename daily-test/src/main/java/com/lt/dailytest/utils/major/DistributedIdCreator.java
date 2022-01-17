package com.lt.dailytest.utils.major;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tong.luo
 * @description DistributedIdCreator
 * @date 2021/7/16 17:34
 */
@Component(value = "primaryKey")
public class DistributedIdCreator implements IMajorKey {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedIdCreator.class);

    private volatile MajorKeyFactory instance;

    @Override
    public String getMajorKey() {
        return getInstance().getMajorKey();
    }

    private MajorKeyFactory getInstance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    long workerId = getWorkerId(getIp());
                    long dataCenterId = getDataCenterId(getCurrentTimeMillis());
                    LOGGER.info("服务启动工作workerId为:{},dataCenterId为:{}", workerId, dataCenterId);
                    instance = new MajorKeyFactory(workerId, dataCenterId);
                }
            }
        }
        return instance;
    }

    private long getWorkerId(String workerIdKey) {
        String workerId = System.getProperty("worker.id");
        LOGGER.info("服务启动工作workerId为:{}", workerId);
        if (StringUtils.isNotEmpty(workerId)) {
            try {
                return Long.parseLong(workerId) % MajorKeyFactory.maxWorkerId;
            } catch (Exception e) {
                return new BigDecimal(workerIdKey.hashCode() + new Random().nextInt(32)).abs().intValue() % MajorKeyFactory.maxWorkerId;
            }
        } else {
            return new BigDecimal(workerIdKey.hashCode() + new Random().nextInt(32)).abs().intValue() % MajorKeyFactory.maxWorkerId;
        }
    }

    private long getDataCenterId(long dataCenterKey) {
        String dataCenterId = System.getenv("DATA_CENTER_ID");
        LOGGER.info("服务启动工作dataCenterId为:{}", dataCenterId);
        if (StringUtils.isNotEmpty(dataCenterId)) {
            try {
                return Long.parseLong(dataCenterId) % MajorKeyFactory.maxDatacenterId;
            } catch (Exception e) {
                return (dataCenterKey + new Random().nextInt(32)) % MajorKeyFactory.maxDatacenterId;
            }
        } else {
            return (dataCenterKey + new Random().nextInt(32)) % MajorKeyFactory.maxDatacenterId;
        }
    }

    private String getIp() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            //ExceptionHandler.castException(e);

        }
        return "1";
    }

    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 主键生成类
     *
     * @author jwh
     */
    private static class MajorKeyFactory {

        private final static long twepoch = 1288834974657L;

        // 机器标识位数
        private final static long workerIdBits = 5L;

        // 数据中心标识位数
        private final static long datacenterIdBits = 5L;

        // 机器ID最大值
        private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);

        // 数据中心ID最大值
        private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

        // 毫秒内自增位
        private final static long sequenceBits = 12L;

        // 机器ID偏左移12位
        private final static long workerIdShift = sequenceBits;

        // 数据中心ID左移17位
        private final static long datacenterIdShift = sequenceBits + workerIdBits;

        // 时间毫秒左移22位
        private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

        private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

        private long lastTimestamp = -1L;

        private long sequence = 0L;

        private final long workerId;

        private final long datacenterId;

        private MajorKeyFactory(long workerId, long datacenterId) {
            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException("worker Id can't be greater than %d or less than 0");
            }
            if (datacenterId > maxDatacenterId || datacenterId < 0) {
                throw new IllegalArgumentException("datacenter Id can't be greater than %d or less than 0");
            }
            this.workerId = workerId;
            this.datacenterId = datacenterId;
        }

        public synchronized String getMajorKey() {
            long timestamp = timeGen();
            if (timestamp < lastTimestamp) {
                try {
                    throw new Exception("Clock moved backwards.  Refusing to generate id for "
                            + (lastTimestamp - timestamp) + " milliseconds");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (lastTimestamp == timestamp) {
                // 当前毫秒内，则+1
                sequence = (sequence + 1) & sequenceMask;
                if (sequence == 0) {
                    // 当前毫秒内计数满了，则等待下一秒
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0;
            }
            lastTimestamp = timestamp;
            // ID偏移组合生成最终的ID，并返回ID
            long nextId = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                    | (workerId << workerIdShift) | sequence;
            return String.valueOf(nextId);
        }

        private long tilNextMillis(final long lastTimestamp) {
            long timestamp = this.timeGen();
            while (timestamp <= lastTimestamp) {
                timestamp = this.timeGen();
            }
            return timestamp;
        }

        private long timeGen() {
            return System.currentTimeMillis();
        }
    }

//    public static void main(String[] args) throws SocketException, UnknownHostException, InterruptedException {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(), 30, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<Runnable>());
//        final ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<>();
//        int loopval = 100;
//        final CountDownLatch countDownLatch = new CountDownLatch(loopval);
//        for (int i = 0; i < loopval; i++) {
//            DistributedIdCreator distributedIdCreator = new DistributedIdCreator();
//            MajorKeyFactory majorKeyFactory = distributedIdCreator.getInstance();
//            threadPoolExecutor.execute(new Runnable() {
//
//                @Override
//                public void run() {
//                    int i = 0;
//                    while (i < 100000) {
//                        i++;
//                        String key = majorKeyFactory.getMajorKey();
//                        //System.out.println(key);
//                        if (key.length() != 19) {
//                            //System.out.println("主键不合法");
//                            LOGGER.error("主键不合法");
//                            break;
//                        }
//                        if (map.putIfAbsent(key, true) != null) {
//                            //System.out.println("主键冲突");
//                            LOGGER.error("主键冲突");
//                            break;
//                        }
//                    }
//                    countDownLatch.countDown();
//                }
//            });
//            //            threadPoolExecutor.shutdown();
//        }
//        countDownLatch.await();
//        threadPoolExecutor.shutdown();
//    }

}
