package com.lt.springcloudtest.controller;

import com.lt.springcloudtest.service.BinaryCodeService;
import com.lt.springcloudtest.utils.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author tong.luo
 * @description BinaryCodeCOntroller
 * @date 2021/1/27 16:37
 */
@RestController
public class BinaryCodeController {
    @Autowired
    private BinaryCodeService binaryCodeService;

    @RequestMapping("saveBinary")
    public Map saveBinary() {
        this.binaryCodeService.saveBinary();
        Map baseMapOfSuccess = BaseMap.getBaseMapOfSuccess();
        baseMapOfSuccess.put(BaseMap.data, true);
        return baseMapOfSuccess;
    }

    @RequestMapping("queryBinary")
    public Map queryBinary() {
        String binary = this.binaryCodeService.getBinary();
        Map baseMapOfSuccess = BaseMap.getBaseMapOfSuccess();
        baseMapOfSuccess.put(BaseMap.data, binary);
        return baseMapOfSuccess;
    }
}
