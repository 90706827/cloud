package com.zgcenv.authorization.config;

import com.zgcenv.entity.utils.IdUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtherConfig {

    @Bean
    public IdUtils idUtils() {
        return new IdUtils();
    }
}
