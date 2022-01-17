package com.lt.dailytest.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tong.luo
 * @description TestRequestbean
 * @date 2021/7/22 16:58
 */
public class TestRequestbean implements Serializable {
    private static final long serialVersionUID = 425882569488251355L;

    private String monStr;
    private BigDecimal monBig;
    private Date strDate;
    private String strDateValue;


    public String getMonStr() {
        return monStr;
    }

    public void setMonStr(String monStr) {
        this.monStr = monStr;
    }

    public BigDecimal getMonBig() {
        return monBig;
    }

    public void setMonBig(BigDecimal monBig) {
        this.monBig = monBig;
    }

    public Date getStrDate() {
        return strDate;
    }

    public void setStrDate(Date strDate) {
        this.strDate = strDate;
    }

    public String getStrDateValue() {
        return strDateValue;
    }

    public void setStrDateValue(String strDateValue) {
        this.strDateValue = strDateValue;
    }
}
