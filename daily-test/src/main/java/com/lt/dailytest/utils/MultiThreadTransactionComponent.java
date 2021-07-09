package com.lt.dailytest.utils;

import com.lt.dailytest.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * @author tong.luo
 * @description MultiThreadTransactionComponent
 * https://blog.csdn.net/aofavx/article/details/113115856
 * @date 2021/6/30 10:32
 */
public class MultiThreadTransactionComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(MultiThreadTransactionComponent.class);
    private PlatformTransactionManager platformTransactionManager;
    private ThreadPoolExecutor threadPoolExecutor;

    private List<Supplier> supplierList = null;
    // 创建执行计数器
    private CountDownLatch countDownLatch;
    // 是否存在异常
    AtomicReference<Boolean> isError = new AtomicReference<>(false);
    Lock lock = new ReentrantLock();

    /**
     * @param platformTransactionManager
     * @param threadPoolExecutor
     */
    public MultiThreadTransactionComponent(PlatformTransactionManager platformTransactionManager, ThreadPoolExecutor threadPoolExecutor) {
        this.platformTransactionManager = platformTransactionManager;
        this.threadPoolExecutor = threadPoolExecutor;
    }

    /**
     * 添加异步执行的方法程序
     */
    public void addFunction(Supplier supplier) {
        this.lock.lock();
        try {
            if (null == supplierList) {
                supplierList = new ArrayList<>();
            }
            this.supplierList.add(supplier);
        } catch (Exception e) {
            this.LOGGER.error("MultiThreadTransactionComponent.addFunction --> error", e);
        } finally {
            this.lock.unlock();
        }

    }

    /**
     * 使用全局的计数器和异常标记字段，统计个异步线程执行的结果
     * 当所有异步线程执行完之后，根据异常标记字段判断是回滚还是提交事务。
     */
    public void execut() {
        lock.lock();
        try {
            if (null == supplierList) {
                throw new BusinessException("supplierList 为空，是否确认添加了任务");
            }
            countDownLatch = new CountDownLatch(supplierList.size());
            LOGGER.info("异步线程开始：……");
            for (Supplier supplier : supplierList) {
                this.threadPoolExecutor.submit(new TransactionRunnable(platformTransactionManager, supplier));
            }
            countDownLatch.countDown();
            if (isError.get()) {
                LOGGER.error("【多线程事务】事务执行失败，事务已回滚");
                throw new BusinessException("多线程执行失败");
            }
        } catch (Exception e) {
            LOGGER.error("MultiThreadTransactionComponent.execut-->error", e);
            throw e;
        } finally {
            lock.unlock();
        }
    }

    class TransactionRunnable implements Runnable {
        private PlatformTransactionManager platformTransactionManager;
        private Supplier supplier;

        public TransactionRunnable(PlatformTransactionManager platformTransactionManager, Supplier supplier) {
            this.platformTransactionManager = platformTransactionManager;
            this.supplier = supplier;
        }

        @Override
        public void run() {
            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus transaction = this.platformTransactionManager.getTransaction(defaultTransactionDefinition);
            try {
                this.supplier.get();
            } catch (Exception e) {
                isError.set(true);
                LOGGER.error("TransactionRunnable.run 多线程执行失败，{}", e.getMessage());
            }
            countDownLatch.countDown();
            try {
                countDownLatch.await();
                if (isError.get()) {
                    //执行事务回滚
                    platformTransactionManager.rollback(transaction);
                } else {
                    platformTransactionManager.commit(transaction);
                }
            } catch (Exception e) {
                LOGGER.error("TransactionRunnable.run 多线程执行失败，{}", e.getMessage());
            }
        }
    }

}
