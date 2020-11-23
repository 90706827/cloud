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
    private static final Logger logger = LoggerFactory.getLogger(SwaggerProvider.class);

    public static final String API_URI = "/v2/api-docs";

    @Resource
    private final CustomParam customParam;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        customParam.getSwaggerPath().forEach((key, value) -> resources.add(swaggerResource(key,
                value.replace("/**", API_URI))));
        logger.debug("resources:{}", resources.toString());
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