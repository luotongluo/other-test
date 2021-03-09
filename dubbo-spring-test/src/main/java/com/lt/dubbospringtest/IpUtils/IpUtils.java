package com.lt.dubbospringtest.IpUtils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 * @author tong.luo
 * @description IpUtils
 * @date 2021/3/2 11:07
 */
public class IpUtils {
    /**
     * 获取访问者的ip地址
     * 注：要外网访问才能获取到外网地址，如果你在局域网甚至本机上访问，获得的是内网或者本机的ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            //X-Forwarded-For：Squid 服务代理
            String ipAddresses = request.getHeader("X-Forwarded-For");

            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //Proxy-Client-IP：apache 服务代理
                ipAddresses = request.getHeader("Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //WL-Proxy-Client-IP：weblogic 服务代理
                ipAddresses = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //HTTP_CLIENT_IP：有些代理服务器
                ipAddresses = request.getHeader("HTTP_CLIENT_IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //X-Real-IP：nginx服务代理
                ipAddresses = request.getHeader("X-Real-IP");
            }

            //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
            if (ipAddresses != null && ipAddresses.length() != 0) {
                ipAddress = ipAddresses.split(",")[0];
            }

            //还是不能获取到，最后再通过request.getRemoteAddr();获取
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    private static String getIpUrl = "http://whois.pconline.com.cn/ipJson.jsp?json=true";

    /**
     * 调用太平洋网络IP地址查询Web接口（http://whois.pconline.com.cn/），返回ip、地理位置
     */
    public static IpVo getIpVo(String ip) {
        //查本机
        String url = getIpUrl;

        //查指定ip
        if (!StringUtils.isEmpty(ip)) {
            url = getIpUrl + "&ip=" + ip;
        }

        StringBuilder inputLine = new StringBuilder();
        String read;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestProperty("Charset", "GBK");
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "GBK"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
         * {"ip":"119.253.46.42","pro":"北京市","proCode":"110000","city":"北京市","cityCode":"110000","region":"门头沟区"
         * ,"regionCode":"110109","addr":"北京市门头沟区 光环新网联通数据中心","regionNames":"","err":""}
         */
        IpVo ipVo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            //当属性的值为空（null或者""）时，不进行序列化，可以减少数据传输
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            //设置日期格式
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            //转换成IpVo
            ipVo = mapper.readValue(new String(inputLine.toString().getBytes("GBK"), "GBK"), IpVo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipVo;
    }

    /**
     * 直接根据访问者的Request，返回ip、地理位置
     */
    public static IpVo getIpVoByRequest(HttpServletRequest request) {
        return IpUtils.getIpVo(IpUtils.getIpAddr(request));
    }

    public static void main(String[] args) {
        //美国华盛顿州西雅图GitHub
//        String ip = "192.30.255.116";
        String ip = "182.61.128.164";
        IpVo ipVo = IpUtils.getIpVo(null);
        System.out.println(JSON.toJSONString(ipVo));
    }
}
