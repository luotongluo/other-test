package com.lt.dailytest.service.impl;

import com.lt.dailytest.service.BaiDuyunService;
import com.lt.dailytest.utils.common.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author admin
 * @description tong.luo
 * @date 2022/1/4 17:05
 */
@SpringBootTest
class BaiDuyunServiceImplTest {
    @Autowired
    private BaiDuyunService baiDuyunService;

    @Test
    void getApplyCode() {
        String applyCode = this.baiDuyunService.getApplyCode();
        System.out.println(applyCode);
    }

    @Test
    void getAccessTokenBycode() {
        String code = "30b3fdaa916d6a102cbe5883ccb6a24d";
        String applyCode = this.baiDuyunService.getAccessTokenBycode(code);
        System.out.println(applyCode);
    }

    private String accessToken = "121.bf67095226236509948c0852aca2f707.YCc29PhS1K_8ro7rScgN5G9aNZoh1xA8-LtuxzD.UZlH_g ";

    @Test
    void refreshToken() {
        String applyCode = this.baiDuyunService.refreshToken(accessToken);
        System.out.println(applyCode);
    }

    @Test
    void getUserGrantInfo() {
        String applyCode = this.baiDuyunService.getUserGrantInfo(accessToken);
        System.out.println(applyCode);
    }

    @Test
    void getYunPanLimitInfo() {
        String applyCode = this.baiDuyunService.getYunPanLimitInfo(accessToken);
        System.out.println(applyCode);
    }

    public static void main(String[] args) {
        String longToStringDate = DateUtils.longToStringDate(1641266283000L);
        System.out.println(longToStringDate);
    }
}