package com.lt.dailytest.service;

import com.lt.dailytest.entity.StockMainTable;

import java.util.List;

/**
 * @author tong.luo
 * @description StockTableService
 * @date 2021/7/14 14:02
 */
public interface StockTableService {
    /**
     * 同步所有数据
     */
    public void synAllData();
    public void initAllData();

    /**
     * 拆分数据
     */
    public void splateCurrDayAllData();
    /**
     * 同步one数据
     */
    public void synOneData(String stockNum);

    void synAllDataOnce();

    List<StockMainTable> getData();

}
