package com.lt.springcloudtest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author tong.luo
 * @description PropertoesConfig
 * @date 2021/1/20 17:58
 */
@Component
@ConfigurationProperties(prefix = "file")
public class PropertoesConfig {
    private String properties;

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
