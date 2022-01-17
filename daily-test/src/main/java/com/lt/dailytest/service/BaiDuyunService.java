package com.lt.dailytest.service;

/**
 * @author admin
 * @description tong.luo
 * @date 2022/1/4 16:40
 */
public interface BaiDuyunService {
    /**
     * 获取用户的授权码
     */
    public String getApplyCode();

    /**
     * code获取授权access_token
     * 第一步获取的授权码
     *
     * @return
     */
    public String getAccessTokenBycode(String code);

    /**
     * 按需刷新access_token
     * 当access_token过期后，可以使用refresh_token进行刷新。refresh_token有效期为十年。
     *
     * @return
     */
    public String refreshToken(String code);

    /**
     * 获取access_token之后，开发者可以通过access_token拉取用户信息。
     *
     * @return
     */
    public String getUserGrantInfo(String accessToken);

    /**
     * 本接口用于获取用户的网盘空间使用情况。
     *
     * @return
     */
    public String getYunPanLimitInfo(String accessToken);
}
