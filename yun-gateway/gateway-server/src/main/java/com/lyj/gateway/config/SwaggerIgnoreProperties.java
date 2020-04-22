package com.lyj.gateway.config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author lyj
 * @date 2019/10/30
 * swagger忽略 服务
 */
@Data
@Configuration
@RefreshScope
@ConditionalOnExpression("!'${swagger}'.isEmpty()")
@ConfigurationProperties(prefix = "swagger")
public class SwaggerIgnoreProperties {

	private List<String> ignoreServers=new ArrayList<>();

}
