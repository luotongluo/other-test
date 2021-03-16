package com.lt.factorytest.shareelements;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * @author tong.luo
 * @description ReportManagerFactory
 * 享元模式中的核心内容
 * 以租客id为索引，维护了一个襄垣对象的集合，它确保相同租客的请求都返回同一个享元实例，确保享元对象
 * 的有效复用
 * @date 2021/2/8 11:41
 */
public class ReportManagerFactory {
    HashMap<String, IReportManager> managerHashMap = new HashMap<>(16);
    HashMap<String, IReportManager> employeeHashMap = new HashMap<>(16);

    IReportManager getFinancialReportManager(String tenantid) {
        IReportManager iReportManager = managerHashMap.get(tenantid);
        if (null == iReportManager) {
            iReportManager = new ReportManagerImpl(tenantid);
            managerHashMap.put(tenantid, iReportManager);
        }
        return iReportManager;
    }

    IReportManager getEmployeeReportMange(String tenantid) {
        IReportManager iReportManager = employeeHashMap.get(tenantid);
        if (null == iReportManager) {
            iReportManager = new EmployeeRepoerManager(tenantid);
            managerHashMap.put(tenantid, iReportManager);
        }
        return iReportManager;
    }

    public static void main(String[] args) {
        ReportManagerFactory reportManagerFactory = new ReportManagerFactory();
        IReportManager reportManager = reportManagerFactory.getFinancialReportManager("A");
        System.out.println(JSON.toJSONString(reportManager));
    }
}
