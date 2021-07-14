package com.lt.dailytest.service.impl;

import com.lt.dailytest.dao.StockBuyTableMapper;
import com.lt.dailytest.dao.StockMainTableMapper;
import com.lt.dailytest.dao.StockSellTableMapper;
import com.lt.dailytest.dao.StockTableMapper;
import com.lt.dailytest.entity.StockBuyTable;
import com.lt.dailytest.entity.StockMainTable;
import com.lt.dailytest.entity.StockSellTable;
import com.lt.dailytest.entity.StockTable;
import com.lt.dailytest.service.StockTableService;
import com.lt.dailytest.utils.DateUtils;
import com.lt.dailytest.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author tong.luo
 * @description StockTableServiceImpl
 * @date 2021/7/14 14:03
 */
@Service
public class StockTableServiceImpl implements StockTableService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockTableServiceImpl.class);

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    @Autowired
    StockTableMapper stockTableDao;
    @Autowired
    StockBuyTableMapper stockBuyTabseMapper;
    @Autowired
    StockMainTableMapper stockMainTableMapper;
    @Autowired
    StockSellTableMapper stockSellTableMapper;


    private List<String> nowInLowList = new ArrayList<>(1024);
    private List<String> nowInUseList = new ArrayList<>(1024);

    /**
     * 同步所有数据
     */
    @Override
    public void synAllData() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                if (CollectionUtils.isEmpty(nowInUseList)) {
                    loopGetInfo();
                } else {
                    loopGetInfo(nowInUseList);
                }

                LOGGER.info("doAssable logic cost time:[{}]", (System.currentTimeMillis() - startTime));
            }
        }, 0, 1800 * 1000);
        while (true) {
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                LOGGER.error("thread sleep error", e);
            }
        }
    }

    /**
     * 拆分数据
     */
    @Override
    public void splateCurrDayAllData() {
        StockTable stockTable = new StockTable();
        String formatDate = DateUtils.formatDate(new Date(), DateUtils.yyyy_MM_dd);

        stockTable.setDealDateBegin(DateUtils.parseDate(formatDate + " 00:00:00", DateUtils.yyyy_MM_dd_hh_mm));
        stockTable.setDealDateEnd(DateUtils.parseDate(formatDate + " 59:59:59", DateUtils.yyyy_MM_dd_hh_mm));
        List<StockTable> stockTables = this.stockTableDao.queryAll(stockTable);
        if (CollectionUtils.isEmpty(stockTables)) {
            return;
        }
        int size = stockTables.size();
        int skipNum = 3000;
        Integer divide = new BigDecimal(String.valueOf(size)).divide(new BigDecimal(String.valueOf(skipNum)), BigDecimal.ROUND_UP).intValue();
        for (int i = 0; i < divide; i++) {
            List<StockTable> stockTableList = stockTables.stream().skip(i * skipNum).limit(skipNum).collect(Collectors.toList());
            List<StockMainTable> stockMainTableList = new ArrayList<>();
            List<StockBuyTable> stockBuyTables = new ArrayList<>();
            List<StockSellTable> stockSellTables = new ArrayList<>();
            for (StockTable table : stockTableList) {
                StockMainTable stockMainTable = new StockMainTable();
                BeanUtils.copyProperties(table, stockMainTable);
                stockMainTable.setId(null);
                stockMainTable.setCreateTime(new Date());
                stockMainTable.setUpdateTime(new Date());
                String stockName = stockMainTable.getStockName();
                String[] split = stockName.split("\"");
                if (split.length > 1) {
                    stockMainTable.setStockName(split[1]);
                }
                stockMainTableList.add(stockMainTable);
                StockBuyTable stockBuyTable = new StockBuyTable();
                BeanUtils.copyProperties(table, stockBuyTable);
                stockBuyTable.setId(null);
                stockBuyTable.setCreateTime(new Date());
                stockBuyTable.setUpdateTime(new Date());
                stockBuyTables.add(stockBuyTable);
                StockSellTable stockSellTable = new StockSellTable();
                BeanUtils.copyProperties(table, stockSellTable);
                stockSellTable.setId(null);
                stockSellTable.setCreateTime(new Date());
                stockSellTable.setUpdateTime(new Date());
                stockSellTables.add(stockSellTable);
            }
            if(CollectionUtils.isEmpty(stockMainTableList) || CollectionUtils.isEmpty(stockBuyTables)|| CollectionUtils.isEmpty(stockSellTables)){
                return;
            }
            this.stockMainTableMapper.deleteBystockNums(stockMainTableList.stream().map(StockMainTable::getStockNum).collect(Collectors.toList()),formatDate);
            this.stockBuyTabseMapper.deleteBystockNums(stockBuyTables.stream().map(StockBuyTable::getStockNum).collect(Collectors.toList()),formatDate);
            this.stockSellTableMapper.deleteBystockNums(stockSellTables.stream().map(StockSellTable::getStockNum).collect(Collectors.toList()),formatDate);
            this.stockMainTableMapper.insertBatch(stockMainTableList);
            this.stockBuyTabseMapper.insertOrUpdateBatch(stockBuyTables);
            this.stockSellTableMapper.insertBatch(stockSellTables);
        }



    }

    @Override
    public void synOneData(String stockNum) {
        if (StringUtils.isEmpty(stockNum)) {
            return;
        }
        this.doAssableInsertTable(stockNum);
    }

    private void loopGetInfo(List<String> nowInUseList) {
        for (String stockNum : nowInUseList) {
            this.runTask(stockNum);
        }
    }

    private void loopGetInfo() {
        //上证范围
//        for (int i = 600000; i < 609000; i++) {
//            String stockNum = i + "";
//            this.runTask(stockNum);
//        }
//        //创业板范围，后续可能需要加大
//        for (int i = 300000; i < 302999; i++) {
//            String stockNum = i + "";
//            this.runTask(stockNum);
//        }
//        for (int i = 1600; i < 2999; i++) {
//            String stockNum = "00" + i;
//            this.runTask(stockNum);
//        }
        for (int i = 100000; i < 1000000; i++) {
            String stockNum = "" + i;
            this.runTask(stockNum);
        }
        for (int i = 10000; i < 100000; i++) {
            String stockNum = "0" + i;
            this.runTask(stockNum);
        }
        for (int i = 1000; i < 10000; i++) {
            String stockNum = "00" + i;
            this.runTask(stockNum);
        }
        for (int i = 100; i < 1000; i++) {
            String stockNum = "000" + i;
            this.runTask(stockNum);
        }
        for (int i = 10; i < 100; i++) {
            String stockNum = "0000" + i;
            this.runTask(stockNum);
        }
        for (int i = 1; i < 10; i++) {
            String stockNum = "00000" + i;
            this.runTask(stockNum);
        }
    }

    private void runTask(String stockNum) {
        if (!nowInUseList.contains(stockNum)) {
            nowInUseList.add(stockNum);
        }
        threadPoolExecutor.execute(() -> {
            try {
                this.doAssableInsertTable(stockNum);
            } catch (Exception e) {
                LOGGER.error("stocknum = [{}]", stockNum, e);
            } catch (Error e) {
                LOGGER.error("stocknum = [{}]", stockNum, e);
            }

        });
    }

    /**
     * 处理单个代码 如果存在则进行更新
     *
     * @param stockNum
     */
    private void doAssableInsertTable(String stockNum) {
        String url = "http://hq.sinajs.cn/list=sh" + stockNum;
        long urlStart = System.currentTimeMillis();
        String s = HttpUtils.get(url);
        //var hq_str_sh601006="大秦铁路,6.070,6.040,6.010,6.100,6.000,6.010,6.020,38456996,232152112.000,
        // 1038213,6.010,2976128,6.000,871500,5.990,977700,5.980,220400,5.970,27200,6.020,630800,6.030,
        // 716475,6.040,272600,6.050,207600,6.060,2021-07-12,15:00:00,00,";

        //System.out.println(s);
        String[] split = s.split(",");
        if (split.length < 25) {
            LOGGER.info("stockNum:[{}],ret:[{}],cost:[{}]", stockNum, s, (System.currentTimeMillis() - urlStart));
            //nowInLowList.add(stockNum);
            return;
        }
        StockTable stockTable = this.getStockTable(stockNum, split);
        StockTable table = new StockTable();
        table.setStockNum(stockNum);
        Date date = Calendar.getInstance().getTime();
        List<StockTable> stockTables = this.stockTableDao.queryAll(table);
        if (CollectionUtils.isEmpty(stockTables)) {
            stockTable.setCreateTime(date);
            stockTable.setUpdateTime(date);
            this.stockTableDao.insert(stockTable);
        } else {
            stockTable.setUpdateTime(date);
            this.stockTableDao.update(stockTable);
        }
    }

    private StockTable getStockTable(String stockNum, String[] split) {
        StockTable stockTable = new StockTable();
//        stockTable.setId(0);
        stockTable.setStockName(split[0]);
        stockTable.setStockNum(stockNum);
        stockTable.setBeginPrice(split[1]);
        stockTable.setLastDayEndPrice(split[2]);
        stockTable.setCurrPrice(split[3]);
        stockTable.setDayMaxPrice(split[4]);
        stockTable.setDayMinPrice(split[5]);
        stockTable.setBuyOne(split[6]);
        stockTable.setSellOne(split[7]);
        stockTable.setSellDoneNum(split[8]);
        stockTable.setSellDoneMon(split[9]);
        stockTable.setBuyOneNum(split[10]);
        stockTable.setBuyOneMon(split[11]);
        stockTable.setBuyTwoNum(split[12]);
        stockTable.setBuyTwoMon(split[13]);
        stockTable.setBuyThreeNum(split[14]);
        stockTable.setBuyThreeMon(split[15]);
        stockTable.setBuyForeNum(split[16]);
        stockTable.setBuyForeMon(split[17]);
        stockTable.setBuyFiveNum(split[18]);
        stockTable.setBuyFiveMon(split[19]);
        stockTable.setSellOneNum(split[20]);
        stockTable.setSellOneMon(split[21]);
        stockTable.setSellTwoNum(split[22]);
        stockTable.setSellTwoMon(split[23]);
        stockTable.setSellThreeNum(split[24]);
        stockTable.setSellThreeMon(split[25]);
        stockTable.setSellForeNum(split[26]);
        stockTable.setSellForeMon(split[27]);
        stockTable.setSellFiveNum(split[28]);
        stockTable.setSellFiveMon(split[29]);
        if (StringUtils.isEmpty(split[31])) {
            String date = split[30];
            Date parseDate = DateUtils.parseDate(date, DateUtils.yyyy_MM_dd);
            stockTable.setDealDate(parseDate);
        } else {
            String date = split[30] + " " + split[31] + "." + split[32];
            Date parseDate = DateUtils.parseDate(date, DateUtils.yyyy_MM_dd_hh_mm_ss);
            stockTable.setDealDate(parseDate);
        }

        return stockTable;
    }
}
