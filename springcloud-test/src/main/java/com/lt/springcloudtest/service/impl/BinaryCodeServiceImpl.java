package com.lt.springcloudtest.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.springcloudtest.bean.TestBinaryCode;
import com.lt.springcloudtest.mapper.TestBinaryCodeMapper;
import com.lt.springcloudtest.service.BinaryCodeService;
import com.lt.springcloudtest.utils.BaseMap;
import com.lt.springcloudtest.utils.BinaryCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author tong.luo
 * @description BinaryCodeServiceImpl
 * @date 2021/1/27 16:38
 */
@Service("BinaryCodeServiceImpl")
public class BinaryCodeServiceImpl implements BinaryCodeService {
    @Autowired
    private TestBinaryCodeMapper testBinaryCodeMapper;

    private String path = "erwei.jpg";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBinary() {
        String data = "test-msg-是打发打发手动阀手动阀手动阀手动阀";

        Map baseMapOfSuccess = BaseMap.getBaseMapOfSuccess();
        baseMapOfSuccess.put(BaseMap.data, data);
        String msg = JSON.toJSONString(baseMapOfSuccess);
        System.out.println("json-->msg:" + msg);
        boolean encode = false;
        try {
            encode = BinaryCodeUtils.encode(msg, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (encode) {
            TestBinaryCode testBinaryCode = new TestBinaryCode();
            testBinaryCode.setUrl(msg);
            Date date = new Date();
            testBinaryCode.setCreateTime(date);
            testBinaryCode.setUpdateTime(date);
            testBinaryCode.setStatus(0);
            testBinaryCode.setCreateId("-1");

            this.testBinaryCodeMapper.insert(testBinaryCode);
        }
    }

    @Override
    public String getBinary() {
        String decode = BinaryCodeUtils.decode(path);
        TestBinaryCode testBinaryCode = new TestBinaryCode();
        testBinaryCode.setUrl(decode);
        List<TestBinaryCode> testBinaryCodes = this.testBinaryCodeMapper.queryAll(testBinaryCode);
        return JSON.toJSONString(testBinaryCodes);
    }
}
