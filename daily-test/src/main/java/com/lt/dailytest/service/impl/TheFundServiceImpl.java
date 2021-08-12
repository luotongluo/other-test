package com.lt.dailytest.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.service.TheFundService;
import com.lt.dailytest.to.TheFundTo;
import com.lt.dailytest.utils.HttpUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author tong.luo
 * @description TheFundServiceImpl
 * @date 2021/8/4 10:58
 */
@Service
public class TheFundServiceImpl implements TheFundService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TheFundServiceImpl.class);

    /**
     * @param code
     * @return
     */
    @Override
    public String getFundOne(String code) {
        //数据链接
//        String referer = "http://so.eastmoney.com/web/s?keyword="+code+"";
        long time = System.currentTimeMillis();
//
//        String url = "http://push2.eastmoney.com/api/qt/stock/get?ut=fa5fd1943c7b386f172d6893dbfba10b&fltt" +
//                "=2&fields=f59,f169,f170,f161,f163,f171,f126,f168,f164,f78,f162,f43,f46,f44,f45,f60,f47," +
//                "f48,f49,f84,f116,f55,f92,f71,f50,f167,f117,f85,f84,f58,f57,f86,f172,f108,f118,f107,f164," +
//                "f177&invt=2&secid=0."+code+"&cb=jQuery1124006112441213993569_1587006450385&_=1587006450403";
//        url = String.format(url,code);
//        Header header = new BasicHeader("Referer", referer);
//        String ret = HttpUtils.httpGet(url, header);
        String url = "http://fundgz.1234567.com.cn/js/" + code + ".js?rt=1589463125600";
        String httpGet = HttpUtils.httpGet(url);
        LOGGER.info("cost : [{}],url:[{}]", (System.currentTimeMillis() - time), url);
        //"代号：" +
        //                    jsonObject.getString("fundcode") +
        //                    "\n" +
        //                    "名称：" +
        //                    jsonObject.getString("name") +
        //                    "\n" +
        //                    "净值日期：" +
        //                    jsonObject.getString("jzrq") +
        //                    "\n" +
        //                    "单位净值：" +
        //                    jsonObject.getString("dwjz") +
        //                    "\n" +
        //                    "估算净值：" +
        //                    jsonObject.getString("gsz") +
        //                    "\n" +
        //                    "估算增长率：" +
        //                    jsonObject.getString("gszzl") +
        //                    "\n" +
        //                    "估值时间：" +
        //                    jsonObject.getString("gztime") +
        //                    "\n";
        //————————————————
        //版权声明：本文为CSDN博主「qq_24256961」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        //原文链接：https://blog.csdn.net/qq_24256961/article/details/106130384
        return httpGet;
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
        if(StringUtils.isNotEmpty(ret)){
            int indexOfBeg = ret.indexOf("(");
            int indexOfEnd = ret.indexOf(")");
            if(indexOfEnd > indexOfBeg){
                String substring = ret.substring(indexOfBeg + 1, indexOfEnd);
                TheFundTo theFundTo = JSON.parseObject(substring, TheFundTo.class);
            }
        }
        return ret;
    }

    @Override
    public String getAllDataOfTianTianFund() {
        String url = "http://fund.eastmoney.com/js/fundcode_search.js";
        return HttpUtils.httpGet(url);
    }
}
