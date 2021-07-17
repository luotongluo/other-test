package com.lt.dailytest.controller;

import com.alibaba.fastjson.JSONObject;
import com.lt.dailytest.service.StockTableService;
import com.lt.dailytest.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author tong.luo
 * @description ScheduleTime
 * https://www.cnblogs.com/mmzs/p/11557583.html
 * https://www.cnblogs.com/mmzs/p/10161936.html
 * @date 2021/7/17 11:56
 */
//1.主要用于标记配置类，兼备Component的效果。
@Configuration
//开启定时任务
@EnableScheduling
@EnableAsync //开启多线程
public class ScheduleTime {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTime.class);
    @Autowired
    private StockTableService stockTableService;

    /**
     * 每天的1点钟
     */
    @Scheduled(cron = "0 0 1 * * ?")
    @Async
    public void initStockTableData() {
        LOGGER.info("initStockTableData --start :time:[{}]", DateUtils.formatDate(new Date(), DateUtils.yyyy_MM_dd_hh_mm_ss));
        this.stockTableService.initAllData();
        LOGGER.info("initStockTableData --end :time:[{}]", DateUtils.formatDate(new Date(), DateUtils.yyyy_MM_dd_hh_mm_ss));
    }

    @Async
    @Scheduled(cron = "0 0 23 * * ?")
    public void spliteData() {
        this.stockTableService.splateCurrDayAllData();
    }

    /**
     * 直接指定时间间隔，例如：5秒
     */
    @Async
    @Scheduled(fixedRate = 10 * 1000)
    public void test() {
        //LOGGER.info("test loading^");
    }
}

@Configuration
@EnableScheduling
class DynamicScheduleTask implements SchedulingConfigurer {

    @Mapper
    public interface CronMapper {
        @Select("select cron,cron_name from cron ")
        public List<JSONObject> getCron();
    }

    @Autowired      //注入mapper
    @SuppressWarnings("all")
    CronMapper cronMapper;

    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行动态定时任务:spliteData " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    List<JSONObject> cronMapperCron = cronMapper.getCron();
                    AtomicReference<String> cron = new AtomicReference<>("");
                    cronMapperCron.stream().forEach(a -> {
                        boolean equals = a.get("cron_name").equals("spliteData");
                        if (equals) {
                            cron.set((String) a.get("cron"));
                            return;
                        }
                    });
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron.get())) {
                        // Omitted Code ..
                        return null;
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron.get()).nextExecutionTime(triggerContext);
                }
        );
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行动态定时任务:initStockTableData " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    List<JSONObject> cronMapperCron = cronMapper.getCron();
                    AtomicReference<String> cron = new AtomicReference<>("");
                    cronMapperCron.stream().forEach(a -> {
                        boolean equals = a.get("cron_name").equals("initStockTableData");
                        if (equals) {
                            cron.set((String) a.get("cron"));
                            return;
                        }
                    });
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron.get())) {
                        // Omitted Code ..
                        return null;
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron.get()).nextExecutionTime(triggerContext);
                }
        );
    }

}