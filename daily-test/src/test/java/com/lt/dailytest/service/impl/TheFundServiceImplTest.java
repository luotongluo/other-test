package com.lt.dailytest.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.entity.fund.FundResTo;
import com.lt.dailytest.service.TheFundService;
import com.lt.dailytest.to.DataFundInfoTo;
import com.lt.dailytest.to.TheFundTo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tong.luo
 * @description TheFundServiceImplTest
 * @date 2021/8/4 11:03
 */
@SpringBootTest
public class TheFundServiceImplTest {
    @Autowired
    private TheFundService theFundService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TheFundServiceImplTest.class);

    @Test
    public void getFundOne() {
        String code = "008983";
        FundResTo fundOne = this.theFundService.getFundOne(code);
        System.out.println(fundOne);
    }

    @Test
    public void getPageListFund(){
        String code = "501005";
        String pageListFund = this.theFundService.getPageListFund(code);
        System.out.println(pageListFund);

    }
    @Test
    public void getAllDataOfTianTianFund(){
        String pageListFund = this.theFundService.getAllDataOfTianTianFund();
        LOGGER.info("pageListFund :[{}]",pageListFund);
    }

    public static void main(String[] args) {
        Executor executor = new ScheduledThreadPoolExecutor(5);
        String ret = "{\"Data\":{\"LSJZList\":[{\"FSRQ\":\"2021-07-28\",\"DWJZ\":\"1.6719\",\"LJJZ\":\"1.6719\",\"SDATE\":null,\"ACTUALSYI\":\"\",\"NAVTYPE\":\"1\",\"JZZZL\":\"2.67\",\"SGZT\":\"开放申购\",\"SHZT\":\"开放赎回\",\"FHFCZ\":\"\",\"FHFCBZ\":\"\",\"DTYPE\":null,\"FHSP\":\"\"},{\"FSRQ\":\"2021-07-27\",\"DWJZ\":\"1.6284\",\"LJJZ\":\"1.6284\",\"SDATE\":null,\"ACTUALSYI\":\"\",\"NAVTYPE\":\"1\",\"JZZZL\":\"-2.76\",\"SGZT\":\"开放申购\",\"SHZT\":\"开放赎回\",\"FHFCZ\":\"\",\"FHFCBZ\":\"\",\"DTYPE\":null,\"FHSP\":\"\"},{\"FSRQ\":\"2021-07-26\",\"DWJZ\":\"1.6746\",\"LJJZ\":\"1.6746\",\"SDATE\":null,\"ACTUALSYI\":\"\",\"NAVTYPE\":\"1\",\"JZZZL\":\"-4.45\",\"SGZT\":\"开放申购\",\"SHZT\":\"开放赎回\",\"FHFCZ\":\"\",\"FHFCBZ\":\"\",\"DTYPE\":null,\"FHSP\":\"\"},{\"FSRQ\":\"2021-07-23\",\"DWJZ\":\"1.7525\",\"LJJZ\":\"1.7525\",\"SDATE\":null,\"ACTUALSYI\":\"\",\"NAVTYPE\":\"1\",\"JZZZL\":\"-2.37\",\"SGZT\":\"开放申购\",\"SHZT\":\"开放赎回\",\"FHFCZ\":\"\",\"FHFCBZ\":\"\",\"DTYPE\":null,\"FHSP\":\"\"}],\"FundType\":\"001\",\"SYType\":null,\"isNewType\":false,\"Feature\":\"020,050,051,054,080\"},\"ErrCode\":0,\"ErrMsg\":null,\"TotalCount\":318,\"Expansion\":null,\"PageSize\":20,\"PageIndex\":1}";
        TheFundTo theFundTo = JSON.parseObject(ret, TheFundTo.class);
        DataFundInfoTo data = theFundTo.getData();
    }
}