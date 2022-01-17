package com.lt.dailytest.to;

import java.io.Serializable;

/**
 * @author tong.luo
 * @description TheFundTo
 * @date 2021/8/4 17:29
 */
public class TheFundTo implements Serializable {
    private static final long serialVersionUID = 3936521205350801938L;
    private DataFundInfoTo Data;
    private String ErrCode;
    private String ErrMsg;
    private Integer TotalCount;
    private String Expansion;
    private Integer PageSize;
    private Integer PageIndex;

    public DataFundInfoTo getData() {
        return Data;
    }

    public void setData(DataFundInfoTo data) {
        Data = data;
    }

    public String getErrCode() {
        return ErrCode;
    }

    public void setErrCode(String errCode) {
        ErrCode = errCode;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public Integer getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(Integer totalCount) {
        TotalCount = totalCount;
    }

    public String getExpansion() {
        return Expansion;
    }

    public void setExpansion(String expansion) {
        Expansion = expansion;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public Integer getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        PageIndex = pageIndex;
    }
}
