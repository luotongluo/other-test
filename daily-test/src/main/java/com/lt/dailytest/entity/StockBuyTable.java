package com.lt.dailytest.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * stock_buy_table(StockBuyTable)实体类
 *
 * @author tong.luo
 * @since 2021-07-14 15:17:45
 */
public class StockBuyTable implements Serializable {
    private static final long serialVersionUID = -33051311163133849L;

    private Integer id;
    /**
     * 股票num
     */
    private String stockNum;
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

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
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
