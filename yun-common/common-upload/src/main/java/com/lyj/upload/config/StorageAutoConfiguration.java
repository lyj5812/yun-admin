package com.lyj.upload.config;
import com.aliyun.oss.OSSClient;
import com.lyj.upload.FileStorage;
import com.lyj.upload.impl.AliyunFileStorage;
import com.lyj.upload.impl.LocalFileStorage;
import com.lyj.upload.properties.LocalStorageProperties;
import com.lyj.upload.properties.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 文件上传自动配置
 *
 * @author lyj
 * @Date 2019-12-23
 */
@Configuration
@EnableConfigurationProperties({OssProperties.class, LocalStorageProperties.class})
public class StorageAutoConfiguration {

    @Autowired
    private OssProperties ossProperties;

    @Autowired
    private LocalStorageProperties localStorageProperties;

    /**
     * 根据配置文件自动装配阿里云SSO存储组件
     *
     * @return AliyunFileStorage.class
     */
    @Bean
    @ConditionalOnProperty(prefix = "yun.storage", name = "type", havingValue = "aliyun" )
    @ConditionalOnMissingBean(FileStorage.class)
    @Order(0)
    public AliyunFileStorage aliyunFileStorage() {
        OSSClient ossClient = new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        AliyunFileStorage aliyunFileUpload = new AliyunFileStorage(ossClient);
        aliyunFileUpload.setBucketName(ossProperties.getBucketName());
        return aliyunFileUpload;
    }

    /**
     * 根据配置文件自动装配本地存储组件
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "yun.storage", name = "type", havingValue = "local" )
    @ConditionalOnMissingBean(FileStorage.class)
    @Order(1)
    public LocalFileStorage localFileStorage() {
        return new LocalFileStorage(localStorageProperties.getLocation());
    }

    /**
     * 默认配置用户目录作为存储目录
     *
     * @return
     */
    @Bean
    @Order(2)
    @ConditionalOnMissingBean(FileStorage.class)
    public LocalFileStorage defaultLocalStorage() {
        return new LocalFileStorage(System.getProperty("user.home"));
    }
}
