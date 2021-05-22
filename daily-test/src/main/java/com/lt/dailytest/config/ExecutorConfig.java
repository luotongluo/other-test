package com.lt.dailytest.config;
import java.util.concurrent.RejectedExecutionHandler;
import org.springframework.core.task.TaskDecorator;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author tong.luo
 * @description ExecutorConfig
 * https://mp.weixin.qq.com/s/D5jlUNyPwIS8rvORCF7GyA
 * @date 2021/5/18 20:44
 */
@Configuration
@EnableAsync
public class ExecutorConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static Integer corePoolSize = Runtime.getRuntime().availableProcessors();
    public static Integer maxPoolSize = Runtime.getRuntime().availableProcessors() * 2;
    public static Integer queueSize = 99999;
    public static String threadPreffix = "test-exec";

    @Bean("getExecutor")
    public Executor getExecutor(){
        logger.info("init …… getExecutor");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setKeepAliveSeconds(0);
        threadPoolTaskExecutor.setQueueCapacity(queueSize);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(false);
        //threadPoolTaskExecutor.setTaskDecorator(new TaskDecorator());
        //threadPoolTaskExecutor.setThreadFactory(new ThreadFactory());
        threadPoolTaskExecutor.setThreadNamePrefix(threadPreffix);
        //不在新线程中执行任务，而是有调用者所在的线程来执行
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(false);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(10);
        //threadPoolTaskExecutor.setAwaitTerminationMillis(10L * 1000);
        threadPoolTaskExecutor.setBeanName(this.getClass().getName());
        //threadPoolTaskExecutor.setThreadNamePrefix("");
        threadPoolTaskExecutor.setThreadPriority(Thread.NORM_PRIORITY);
        threadPoolTaskExecutor.setDaemon(false);
        threadPoolTaskExecutor.setThreadGroupName("");
        //threadPoolTaskExecutor.setThreadGroup(new ThreadGroup());

        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
