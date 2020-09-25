package com.zgcenv.gateway.web.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName GatewayConfig
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-7
 * @Version 1.0
 **/
//@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_server",
                        r -> r.path("/server/**")
                                .uri("lb://alibaba-server"))
                .route("path_client",
                        r -> r.path("/client/**")
                                .uri("lb://alibaba-client"))
                .route("path_config",
                        r -> r.path("/config/**")
                                .uri("lb://config"))
                .route("path_auth",
                        r -> r.path("/auth/**")
                                .uri("lb://auth"))
                .build();
    }

}
