package com.lt.dailytest.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * stock_sell_table(StockSellTable)实体类
 *
 * @author tong.luo
 * @since 2021-07-14 15:17:51
 */
public class StockSellTable implements Serializable {
    private static final long serialVersionUID = 973886270303061677L;

    private Integer id;
    /**
     * 股票num
     */
    private String stockNum;
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
