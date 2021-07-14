package com.lt.dailytest.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * stock_main_table(StockMainTable)实体类
 *
 * @author tong.luo
 * @since 2021-07-14 15:17:48
 */
public class StockMainTable implements Serializable {
    private static final long serialVersionUID = 837392638805050104L;

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
     * deal_date
     */
    private Date dealDate;

    private Date createTime;

    private Date updateTime;


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
