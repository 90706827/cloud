package com.zgcenv.gateway.web.config;

import com.zgcenv.gateway.web.service.RouteService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {
    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);

    public static final String API_URI = "/swagger-ui.html";

    @Resource
    private final RouteService routeService;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        logger.info(resources.toString());
        routeService.getRouteDefinitions().stream()
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition ->
                                "Path".equalsIgnoreCase(predicateDefinition.getName()))
                        .peek(predicateDefinition -> logger.debug("路由配置参数：{}", predicateDefinition.getArgs()))
                        .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
                                predicateDefinition.getArgs().get("pattern").replace("/**", API_URI)))));
        logger.debug("resources:{}", resources);
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setSwaggerVersion("2.0");
        swaggerResource.setUrl(location);
        return swaggerResource;
    }
}