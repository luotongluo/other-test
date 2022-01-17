package com.lt.dailytest.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * stock table(StockTable)实体类
 *
 * @author tong.luo
 * @since 2021-07-12 19:25:08
 */
public class StockTable implements Serializable {
    private static final long serialVersionUID = 358159009861226793L;

    private Integer id;
    /**
     * 股票名字
     */
    private String stockName;
    /**
     * 股票num
     */
    private String stockNum;
    /**
     * 今日开盘价
     */
    private String beginPrice;
    /**
     * 昨日收盘价
     */
    private String lastDayEndPrice;
    /**
     * 当前价格
     */
    private String currPrice;
    /**
     * 今日最高价
     */
    private String dayMaxPrice;
    /**
     * 今日最低价
     */
    private String dayMinPrice;
    /**
     * 竞买价，即“买一”报价
     */
    private String buyOne;
    /**
     * 竞卖价，即“卖一”报价
     */
    private String sellOne;
    /**
     * 成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百
     */
    private String sellDoneNum;
    /**
     * 成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万
     */
    private String sellDoneMon;
    /**
     * 买一”申请4695股，即47手
     */
    private String buyOneNum;
    /**
     * 买一报价
     */
    private String buyOneMon;
    /**
     * 买二
     */
    private String buyTwoNum;
    /**
     * 买二
     */
    private String buyTwoMon;
    /**
     * 买三
     */
    private String buyThreeNum;
    /**
     * 买三
     */
    private String buyThreeMon;
    /**
     * 买四
     */
    private String buyForeNum;
    /**
     * 买四
     */
    private String buyForeMon;
    /**
     * 买五
     */
    private String buyFiveNum;
    /**
     * 买五
     */
    private String buyFiveMon;
    /**
     * “卖一”申报3100股，即31手
     */
    private String sellOneNum;
    /**
     * “卖一”报价
     */
    private String sellOneMon;
    /**
     * 卖二
     */
    private String sellTwoNum;
    /**
     * 卖二
     */
    private String sellTwoMon;
    /**
     * 卖三
     */
    private String sellThreeNum;
    /**
     * 卖三
     */
    private String sellThreeMon;
    /**
     * 卖四
     */
    private String sellForeNum;
    /**
     * 卖四
     */
    private String sellForeMon;
    /**
     * 卖五
     */
    private String sellFiveNum;
    /**
     * 卖五
     */
    private String sellFiveMon;
    /**
     * 卖五
     */
    private Date dealDate;
    private Date dealDateBegin;
    private Date dealDateEnd;

    private Date createTime;

    private Date updateTime;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDealDateBegin() {
        return dealDateBegin;
    }

    public void setDealDateBegin(Date dealDateBegin) {
        this.dealDateBegin = dealDateBegin;
    }

    public Date getDealDateEnd() {
        return dealDateEnd;
    }

    public void setDealDateEnd(Date dealDateEnd) {
        this.dealDateEnd = dealDateEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    public String getBeginPrice() {
        return beginPrice;
    }

    public void setBeginPrice(String beginPrice) {
        this.beginPrice = beginPrice;
    }

    public String getLastDayEndPrice() {
        return lastDayEndPrice;
    }

    public void setLastDayEndPrice(String lastDayEndPrice) {
        this.lastDayEndPrice = lastDayEndPrice;
    }

    public String getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(String currPrice) {
        this.currPrice = currPrice;
    }

    public String getDayMaxPrice() {
        return dayMaxPrice;
    }

    public void setDayMaxPrice(String dayMaxPrice) {
        this.dayMaxPrice = dayMaxPrice;
    }

    public String getDayMinPrice() {
        return dayMinPrice;
    }

    public void setDayMinPrice(String dayMinPrice) {
        this.dayMinPrice = dayMinPrice;
    }

    public String getBuyOne() {
        return buyOne;
    }

    public void setBuyOne(String buyOne) {
        this.buyOne = buyOne;
    }

    public String getSellOne() {
        return sellOne;
    }

    public void setSellOne(String sellOne) {
        this.sellOne = sellOne;
    }

    public String getSellDoneNum() {
        return sellDoneNum;
    }

    public void setSellDoneNum(String sellDoneNum) {
        this.sellDoneNum = sellDoneNum;
    }

    public String getSellDoneMon() {
        return sellDoneMon;
    }

    public void setSellDoneMon(String sellDoneMon) {
        this.sellDoneMon = sellDoneMon;
    }

    public String getBuyOneNum() {
        return buyOneNum;
    }

    public void setBuyOneNum(String buyOneNum) {
        this.buyOneNum = buyOneNum;
    }

    public String getBuyOneMon() {
        return buyOneMon;
    }

    public void setBuyOneMon(String buyOneMon) {
        this.buyOneMon = buyOneMon;
    }

    public String getBuyTwoNum() {
        return buyTwoNum;
    }

    public void setBuyTwoNum(String buyTwoNum) {
        this.buyTwoNum = buyTwoNum;
    }

    public String getBuyTwoMon() {
        return buyTwoMon;
    }

    public void setBuyTwoMon(String buyTwoMon) {
        this.buyTwoMon = buyTwoMon;
    }

    public String getBuyThreeNum() {
        return buyThreeNum;
    }

    public void setBuyThreeNum(String buyThreeNum) {
        this.buyThreeNum = buyThreeNum;
    }

    public String getBuyThreeMon() {
        return buyThreeMon;
    }

    public void setBuyThreeMon(String buyThreeMon) {
        this.buyThreeMon = buyThreeMon;
    }

    public String getBuyForeNum() {
        return buyForeNum;
    }

    public void setBuyForeNum(String buyForeNum) {
        this.buyForeNum = buyForeNum;
    }

    public String getBuyForeMon() {
        return buyForeMon;
    }

    public void setBuyForeMon(String buyForeMon) {
        this.buyForeMon = buyForeMon;
    }

    public String getBuyFiveNum() {
        return buyFiveNum;
    }

    public void setBuyFiveNum(String buyFiveNum) {
        this.buyFiveNum = buyFiveNum;
    }

    public String getBuyFiveMon() {
        return buyFiveMon;
    }

    public void setBuyFiveMon(String buyFiveMon) {
        this.buyFiveMon = buyFiveMon;
    }

    public String getSellOneNum() {
        return sellOneNum;
    }

    public void setSellOneNum(String sellOneNum) {
        this.sellOneNum = sellOneNum;
    }

    public String getSellOneMon() {
        return sellOneMon;
    }

    public void setSellOneMon(String sellOneMon) {
        this.sellOneMon = sellOneMon;
    }

    public String getSellTwoNum() {
        return sellTwoNum;
    }

    public void setSellTwoNum(String sellTwoNum) {
        this.sellTwoNum = sellTwoNum;
    }

    public String getSellTwoMon() {
        return sellTwoMon;
    }

    public void setSellTwoMon(String sellTwoMon) {
        this.sellTwoMon = sellTwoMon;
    }

    public String getSellThreeNum() {
        return sellThreeNum;
    }

    public void setSellThreeNum(String sellThreeNum) {
        this.sellThreeNum = sellThreeNum;
    }

    public String getSellThreeMon() {
        return sellThreeMon;
    }

    public void setSellThreeMon(String sellThreeMon) {
        this.sellThreeMon = sellThreeMon;
    }

    public String getSellForeNum() {
        return sellForeNum;
    }

    public void setSellForeNum(String sellForeNum) {
        this.sellForeNum = sellForeNum;
    }

    public String getSellForeMon() {
        return sellForeMon;
    }

    public void setSellForeMon(String sellForeMon) {
        this.sellForeMon = sellForeMon;
    }

    public String getSellFiveNum() {
        return sellFiveNum;
    }

    public void setSellFiveNum(String sellFiveNum) {
        this.sellFiveNum = sellFiveNum;
    }

    public String getSellFiveMon() {
        return sellFiveMon;
    }

    public void setSellFiveMon(String sellFiveMon) {
        this.sellFiveMon = sellFiveMon;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
