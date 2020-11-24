package com.zgcenv.gateway.web.provider;


import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.gateway.GatewayRoute;
import com.zgcenv.entity.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName GatewayRouteProvider
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-28
 * @Version 1.0
 **/
@FeignClient(name = "organization", fallback = GatewayRouteProviderFallback.class, path = "/organization")
public interface GatewayRouteProvider {

    @GetMapping(value = "/routes/all")
    Resp<List<GatewayRoute>> routeAll();

    @GetMapping(value = "/user/findUserInfoByUsername")
    Resp<UserVo> findUserInfoByUsername(@RequestParam("username") String username);
}