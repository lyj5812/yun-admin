package com.lyj.common.config;
import com.lyj.common.annotation.AuthIgnore;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import cn.hutool.core.util.ReUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @description 忽略服务间 鉴权
 * @date  2019/10/29
 * @author lyj
 **/
@Slf4j
@Configurable
@ConfigurationProperties(prefix = "security.oauth2.client")
public class AuthIgnoreConfig implements InitializingBean {

    @Autowired
    private WebApplicationContext applicationContext;

    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");
    private static final String ASTERISK = "*";

    @Getter
    @Setter
    private List<String> ignoreUrls = new ArrayList<>();

    @Override
    public void afterPropertiesSet(){
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        map.keySet().forEach(mappingInfo -> {
            HandlerMethod handlerMethod = map.get(mappingInfo);

            AuthIgnore method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), AuthIgnore.class);
            Optional.ofNullable(method)
                    .ifPresent(authIgnore -> mappingInfo
                            .getPatternsCondition()
                            .getPatterns()
                            .forEach(url -> ignoreUrls.add(ReUtil.replaceAll(url, PATTERN, ASTERISK))));

            AuthIgnore controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), AuthIgnore.class);
            Optional.ofNullable(controller)
                    .ifPresent(authIgnore -> mappingInfo
                            .getPatternsCondition()
                            .getPatterns()
                            .forEach(url -> ignoreUrls.add(ReUtil.replaceAll(url, PATTERN, ASTERISK))));
        });
    }
}
