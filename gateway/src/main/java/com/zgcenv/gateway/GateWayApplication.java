package com.zgcenv.gateway;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.zgcenv.gateway.config.GatewayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName GateWayApplication
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-4
 * @Version 1.0
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableMethodCache(basePackages = "com.zgcenv.gateway")
@EnableCreateCacheAnnotation
public class GateWayApplication {
    private static final Logger logger = LoggerFactory.getLogger(GatewayConfig.class);

    public static void main(String[] args) throws UnknownHostException {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(GateWayApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        logger.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "Swagger文档: \t\thttp://{}:{}{}{}/doc.html\n\t" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path", ""),
                env.getProperty("spring.mvc.servlet.path", "")
        );
    }

}