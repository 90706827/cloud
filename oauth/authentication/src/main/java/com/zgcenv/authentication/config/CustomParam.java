package com.zgcenv.authentication.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
    /**
     * 终端ID
     **/
    private Long workerId;
    /**
     * 数据中心ID
     **/
	private Long datacenterId;
}
