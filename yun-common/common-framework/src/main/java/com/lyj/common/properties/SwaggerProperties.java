package com.lyj.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "swagger2")
public class SwaggerProperties {

    private String basePackage;

    private String title;

    private String version;

    private String description;

    private String contactName;

    private String contactUrl;

    private String contactEmail;

}
