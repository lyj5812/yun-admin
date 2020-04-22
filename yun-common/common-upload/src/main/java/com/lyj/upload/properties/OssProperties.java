package com.lyj.upload.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云文件上传属性配置
 *
 * @author lyj
 * @date 2019-12-23
 */
@ConfigurationProperties(prefix = "yun.storage.oss" )
public class OssProperties {

    /**
     * endpoint地址
     */
    private String endpoint;

    /**
     * oss key
     */
    private String accessKeyId;

    /**
     * oss密钥
     */
    private String accessKeySecret;

    /**
     * bucket名称
     */
    private String bucketName;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
