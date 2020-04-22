package com.lyj.gateway.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyj
 * @date: 2019/1/21
 */
@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {

    public static final String API_URI = "/v2/api-docs";

    public static final String NACOS_SUB_PRIX = "CompositeDiscoveryClient_";

    private final DiscoveryClientRouteDefinitionLocator routeLocator;

    @Autowired
    private SwaggerIgnoreProperties swaggerIgnoreProperties;

    public SwaggerProvider(DiscoveryClientRouteDefinitionLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        //从DiscoveryClientRouteDefinitionLocator 中取出routes，构造成swaggerResource
        routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
            if (swaggerIgnoreProperties.getIgnoreServers().indexOf(routeDefinition.getId().substring(NACOS_SUB_PRIX.length()))==-1){
                resources.add(swaggerResource(routeDefinition.getId().substring(NACOS_SUB_PRIX.length()), routeDefinition.getPredicates().get(0).getArgs().get("pattern").replace("/**", API_URI)));
            }
        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

}