package com.lyj.upload.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 本地存储属性配置
 *
 * @author lyj
 * @date 2019-12-23
 */
@ConfigurationProperties(prefix = "yun.storage.local" )
public class LocalStorageProperties {
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
