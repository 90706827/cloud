package com.zgcenv.gateway.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName CustomParam
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-10-12
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "custom")
public class CustomParam {
    private Map<String, String> swaggerPath;
}
