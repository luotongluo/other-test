package com.lt.factorytest.shareelements;

/**
 * @author tong.luo
 * @description ReportManagerImpl
 * 财务报表
 * @date 2021/2/8 11:37
 */
public class ReportManagerImpl implements IReportManager {
    //租户id
    protected String tanantId = null;

    public ReportManagerImpl(String tanantId) {
        this.tanantId = tanantId;
    }

    @Override
    public String createReport() {
        return "this is ReportManagerImpl a financial report";
    }
}
