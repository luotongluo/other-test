package com.lt.dailytest.service.impl;

import com.lt.dailytest.entity.RetVo;
import com.lt.dailytest.service.TestService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author tong.luo
 * @description TestServiceImpl
 * @date 2021/8/23 17:27
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public Object getNum() {
        RetVo retVo = new RetVo();
        retVo.setBi(new BigDecimal("123.000000"));
        retVo.setD1(2.200);
        return retVo;
    }
}
