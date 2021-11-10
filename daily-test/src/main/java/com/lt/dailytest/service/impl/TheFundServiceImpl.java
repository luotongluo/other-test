package com.lt.dailytest.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.entity.fund.FundResTo;
import com.lt.dailytest.service.TheFundService;
import com.lt.dailytest.to.TheFundTo;
import com.lt.dailytest.utils.http.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author tong.luo
 * @description TheFundServiceImpl
 * @date 2021/8/4 10:58
 */
@Service
public class TheFundServiceImpl implements TheFundService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TheFundServiceImpl.class);

    private final static String FUND_STR_START = "http://fundgz.1234567.com.cn/js/";
    private final static String FUND_STR_END = ".js?rt=1589463125600";

    /**
     * @param code
     * @return
     */
    @Override
    public FundResTo getFundOne(String code) {
        long time = System.currentTimeMillis();
        String url = FUND_STR_START + code + FUND_STR_END;
        String httpGet = HttpUtils.httpGet(url);
        LOGGER.info("cost : [{}],url:[{}],res:[{}]", (System.currentTimeMillis() - time), url, httpGet);
        //jsonpgz({"fundcode":"001186","name":"富国文体健康股票A","jzrq":"2021-11-01","dwjz":"2.2930","gsz":"2.2995","gszzl":"0.28","gztime":"2021-11-02 11:22"});
        String replace = httpGet.replace("jsonpgz(", "").replace(");", "");
        //System.out.println(replace);
        FundResTo fundResTo = JSON.parseObject(replace, FundResTo.class);
        //原文链接：https://blog.csdn.net/qq_24256961/article/details/106130384
        return fundResTo;
    }

    /**
     * https://blog.csdn.net/weixin_42536058/article/details/114611944
     *
     * @param code
     * @return
     */
    @Override
    public String getPageListFund(String code) {
        Integer pageIndex = 1;
        Integer pageSize = 10;
        String startTime = "2020-04-15";
        String endTime = "2021-08-04";
        String referer = "http://fundf10.eastmoney.com/f10/jjjz_" + code + ".html";
        long time = System.currentTimeMillis();
        String url = "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18306596328894644803_1571038362181&" +
                "fundCode=%s&pageIndex=%s&pageSize=%s&startDate=%s&endDate=%s&_=%s";
        url = String.format(url, code, pageIndex, pageSize, startTime, endTime, time);
        Header header = new BasicHeader("Referer", referer);
        String ret = HttpUtils.httpGet(url, header);
        LOGGER.info("cost : [{}],url:[{}]", (System.currentTimeMillis() - time), url);
        if (StringUtils.isNotEmpty(ret)) {
            int indexOfBeg = ret.indexOf("(");
            int indexOfEnd = ret.indexOf(")");
            if (indexOfEnd > indexOfBeg) {
                String substring = ret.substring(indexOfBeg + 1, indexOfEnd);
                TheFundTo theFundTo = JSON.parseObject(substring, TheFundTo.class);
            }
        }
        return ret;
    }

    @Override
    public String getAllDataOfTianTianFund() {
        //所有基金名称列表代码
//        String url = "http://fund.eastmoney.com/js/fundcode_search.js";
        String url = "http://fund.eastmoney.com/js/jjjz_gs.js?dt=1463791574015";
        //所有基金公司名称列表代码  http://fund.eastmoney.com/js/jjjz_gs.js?dt=1463791574015
//        String url = "http://fund.eastmoney.com/pingzhongdata/001186.js?v=20160518155842";
        //基金详细信息：http://fund.eastmoney.com/pingzhongdata/001186.js?v=20160518155842

        return HttpUtils.httpGet(url);
    }
}
