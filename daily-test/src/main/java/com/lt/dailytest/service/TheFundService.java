package com.lt.dailytest.service;


/**
 * @author tong.luo
 * @description TheFundService
 * @date 2021/8/4 10:57
 */
public interface TheFundService {
    /**
     * @param code
     * @return
     */
    public String getFundOne(String code);

    /**
     * @param code
     * @return
     */
    public String getPageListFund(String code);

    public String getAllDataOfTianTianFund();
}
