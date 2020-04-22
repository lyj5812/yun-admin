package com.lyj.common.properties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
@Configuration
@RefreshScope
@ConfigurationProperties(
        prefix = "oauth2"
)
public class YunResourceServerProperties {

    private List<String> ignorePatterns=new ArrayList<>();

}
