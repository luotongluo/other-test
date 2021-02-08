package com.lt.factorytest.shareelements;

/**
 * @author tong.luo
 * @description EmployeeRepoerManager
 * @date 2021/2/8 11:40
 */
public class EmployeeRepoerManager implements IReportManager {
    //租户id
    protected String tanantId = null;

    public EmployeeRepoerManager(String tanantId) {
        this.tanantId = tanantId;
    }
    @Override
    public String createReport() {
        return "this is a EmployeeRepoerManager financial report";
    }
}
