package com.lt.dailytest.to;

import java.util.List;

/**
 * @author tong.luo
 * @description DataFundInfoTo
 * @date 2021/8/4 17:33
 */
public class DataFundInfoTo {
    private List<BasicFundInfoTO> LSJZList;
    private String FundType;
    private String SYType;
    private Boolean isNewType;
    private String Feature;

    public List<BasicFundInfoTO> getLSJZList() {
        return LSJZList;
    }

    public void setLSJZList(List<BasicFundInfoTO> LSJZList) {
        this.LSJZList = LSJZList;
    }

    public String getFundType() {
        return FundType;
    }

    public void setFundType(String fundType) {
        FundType = fundType;
    }

    public String getSYType() {
        return SYType;
    }

    public void setSYType(String SYType) {
        this.SYType = SYType;
    }

    public Boolean getNewType() {
        return isNewType;
    }

    public void setNewType(Boolean newType) {
        isNewType = newType;
    }

    public String getFeature() {
        return Feature;
    }

    public void setFeature(String feature) {
        Feature = feature;
    }
}
