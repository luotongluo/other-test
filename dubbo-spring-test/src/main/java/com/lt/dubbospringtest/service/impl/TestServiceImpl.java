package com.lt.dubbospringtest.service.impl;

import com.lt.dubbospringtest.service.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author tong.luo
 * @description TestServiceImpl
 * @date 2021/2/26 10:27
 */
@Service
public class TestServiceImpl implements TestService {

//    @DubboReference
//    private IInvoiceBusinessQrcodeService iInvoiceBusinessQrcodeService;



    /**
     * 测试方法
     *
     * @return
     */
    @Override
    public String test() {
        return "ettest";
    }

    /**
     * testDubboRef
     */
    @Override
    public void testDubboRef() {
//        InvoiceBusinessQrcodeCreateInfo businessQrcodeWrapById = this.iInvoiceBusinessQrcodeService.getBusinessQrcodeWrapById("1364134518122876928");
//        String s = JSON.toJSONString(businessQrcodeWrapById);
//        System.out.println(s);
    }


}
