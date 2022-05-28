package com.lt.jdk11test.test.impl;

import com.lt.jdk11test.test.service.TestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author 12828
 * @description TestServiceImpl
 * @date 2022/05/07 11:32
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String testMethod() {
        return String.valueOf(new ArrayList<>());
    }
}
