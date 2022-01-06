package com.lt.dailytest.service.impl;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.exception.BusinessException;
import com.lt.dailytest.service.BaiDuyunService;
import com.lt.dailytest.utils.common.StringUtils;
import com.lt.dailytest.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author admin
 * @description tong.luo
 * @date 2022/1/4 16:41
 */
@Service
public class BaiDuyunServiceImpl implements BaiDuyunService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaiDuyunServiceImpl.class);
    private static final String AppID = "25458214";
    private static final String Appkey = "xGHLC5FKpC8VtuHxPi1EjXZgnW3E8Q9h";
    private static final String Secretkey = "l5rA2Nrg59FV1twsgG5zwh5e5T2AWCDQ";
    private static final String Signkey = "c53Kj@n%!e!#STScwie!QK8!gg4HkUL3";
    private static final String RedrictUri = "oob";
    private static final String scope = "basic";//mobile

    private static final String requestUrl = "https://openapi.baidu.com/oauth/2.0";
    private static final String requestAuthBaseUrl = "https://openapi.baidu.com/oauth/2.0/";

    /**
     * https://openauth.baidu.com/doc/doc.html#_2-%E5%BC%95%E5%AF%BC%E7%94%A8%E6%88%B7%E5%AE%8C%E6%88%90%E6%8E%88%E6%9D%83%E8%8E%B7%E5%8F%96code
     *
     * @return
     */
    @Override
    public String getApplyCode() {
        //https://openapi.baidu.com/oauth/2.0/authorize?response_type=code&client_id=xGHLC5FKpC8VtuHxPi1EjXZgnW3E8Q9h&redirect_uri=oob&scope=basic&state=state
        String url = requestAuthBaseUrl + "/authorize?response_type=code&client_id=" + Appkey + "&redirect_uri=" + RedrictUri + "&scope=" + scope + "&state=state";
        String httpGet = HttpUtils.httpGet(url);
        LOGGER.info("获取code 请求url：{},res:[{}]", url, httpGet);
        return httpGet;
    }

    /**
     * code获取授权access_token
     * https://openauth.baidu.com/doc/doc.html#_2-%E5%BC%95%E5%AF%BC%E7%94%A8%E6%88%B7%E5%AE%8C%E6%88%90%E6%8E%88%E6%9D%83%E8%8E%B7%E5%8F%96code
     *
     * @return
     */
    @Override
    public String getAccessTokenBycode(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new BusinessException("请求参数为空");
        }
        String url = requestAuthBaseUrl + "token?grant_type=authorization_code&code=" + code + "&client_id=" + Appkey + "&client_secret=" + Secretkey + "&redirect_uri=" + RedrictUri;
        String httpGet = HttpUtils.httpGet(url);
        LOGGER.info("code获取授权access_token 请求url：[{}],res:[{}]", url, httpGet);
        Map map = JSON.parseObject(httpGet, Map.class);
        Object accessToken = map.get("access_token");
        Object refreshToken = map.get("refresh_token");
        LOGGER.info("accessToken : [{}],refreshToken : [{}],", accessToken, refreshToken);
        return httpGet;
    }

    /**
     * 按需刷新access_token
     * 当access_token过期后，可以使用refresh_token进行刷新。refresh_token有效期为十年。
     *
     * @param refreshToken
     * @return
     */
    @Override
    public String refreshToken(String refreshToken) {
        if (StringUtils.isEmpty(refreshToken)) {
            throw new BusinessException("请求参数为空");
        }
        String url = requestAuthBaseUrl + "token?grant_type=refresh_token&refresh_token=" + refreshToken + "&client_id=" + Appkey + "&client_secret=" + Secretkey;
        String httpGet = HttpUtils.httpGet(url);
        LOGGER.info("按需刷新access_token 请求url：[{}],res:[{}]", url, httpGet);
        Map map = JSON.parseObject(httpGet, Map.class);
        Object accessToken = map.get("access_token");
        Object refreshTokenres = map.get("refresh_token");
        LOGGER.info("accessToken : [{}],refreshToken : [{}],", accessToken, refreshTokenres);
        return httpGet;
    }

    /**
     * 获取access_token之后，开发者可以通过access_token拉取用户信息。
     *
     * @param accessToken
     * @return
     */
    @Override
    public String getUserGrantInfo(String accessToken) {
        if (StringUtils.isEmpty(accessToken)) {
            throw new BusinessException("请求参数为空");
        }
        String url = "https://openapi.baidu.com/rest/2.0/passport/users/getInfo?access_token=" + accessToken;
        String httpGet = HttpUtils.httpGet(url);
        LOGGER.info("按需刷新access_token 请求url：[{}],res:[{}]", url, httpGet);
        return httpGet;
    }

    /**
     * 本接口用于获取用户的网盘空间使用情况。
     * pan.baidu.com/union/doc/Cksg0s9ic
     *
     * @param accessToken
     * @return
     */
    @Override
    public String getYunPanLimitInfo(String accessToken) {
        if (StringUtils.isEmpty(accessToken)) {
            throw new BusinessException("请求参数为空");
        }
        String url = "https://pan.baidu.com/api/quota?access_token=" + accessToken + "&checkfree=1&checkexpire=1";
        String httpGet = HttpUtils.httpGet(url);
        LOGGER.info("按需刷新access_token 请求url：[{}],res:[{}]", url, httpGet);
        return httpGet;
    }


}
