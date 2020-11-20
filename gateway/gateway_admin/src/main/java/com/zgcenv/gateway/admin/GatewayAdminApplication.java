package com.zgcenv.gateway.admin;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayClassPathWarningAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName GatewayAdminApplication
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-25
 * @Version 1.0
 **/

@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.zgcenv.gateway.admin.dao")
@EntityScan("com.zgcenv.entity.gateway")
@EnableDiscoveryClient
@EnableMethodCache(basePackages = "com.zgcenv.gateway")
@EnableCreateCacheAnnotation
@SpringBootApplication(scanBasePackages = {"com.alicp.jetcache.autoconfigure","com.zgcenv.gateway"}, exclude = GatewayClassPathWarningAutoConfiguration.class)
public class GatewayAdminApplication {

    public static void main(String[] args) {

        SpringApplication.run(GatewayAdminApplication.class, args);
    }
}
