package com.zgcenv.organization;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName AuthApplication
 * @Description 认证中心
 * @Author Mr.Jangni
 * @Date 2020-9-14
 * @Version 1.0
 **/

@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.zgcenv.organization.dao")
@EntityScan(basePackages = {"com.zgcenv.entity.organization", "com.zgcenv.entity.gateway"})
@EnableMethodCache(basePackages = "com.zgcenv.organization")
@EnableCreateCacheAnnotation
public class OrganizationApplication {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationApplication.class);


    public static void main(String[] args) throws UnknownHostException {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(OrganizationApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        logger.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "Swagger文档: \thttp://{}:{}{}{}/doc.html\n\t" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path", ""),
                env.getProperty("spring.mvc.servlet.path", "")
        );
    }

    @Value("${test:NO LOADING...}")
    private String test;

    @PostConstruct
    public void testConfig() {
        logger.info("\n---------------------\n----test:{}\n---------------------", test);
    }

    @RequestMapping("/test")
    public String name() {
        return test;
    }
}
