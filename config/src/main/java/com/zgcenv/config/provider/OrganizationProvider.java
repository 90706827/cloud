package com.zgcenv.config.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.gateway.GatewayRoute;
import com.zgcenv.entity.organization.Resource;
import com.zgcenv.entity.organization.Users;
import feign.Feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName OrganizationProvider
 * @Description 熔断机制接口
 * @Author Mr.Jangni
 * @Date 2020-9-16
 * @Version 1.0
 **/
@FeignClient(name = "organization", fallback = OrganizationProviderFallback.class, path = "/organization")
public interface OrganizationProvider {

    @GetMapping(value = "/user/findUserByUsername")
    Resp<Users> findUserByUsername(@RequestParam("username") String username);

    @GetMapping(value = "/resource/all")
    Resp<List<Resource>> resources();

    @GetMapping(value = "/resource/user")
    Resp<List<Resource>> resources(@RequestParam("username") String username);

    @GetMapping(value = "/routes/all")
    Resp<List<GatewayRoute>> routeAll();
}
//懒加载 解决第一次加载超时问题
class FooConfiguration {
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }
}
