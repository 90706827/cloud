package com.zgcenv.gateway.provider;

import com.zgcenv.core.context.Resp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @InterfaceName AuthProvider
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/

@Component
@FeignClient(name = "authentication", fallback = AuthProviderFallback.class, path = "/organization")
public interface AuthProvider {
    @PostMapping(value = "/auth/permission")
    Resp auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication,
              @RequestParam("url") String url,
              @RequestParam("method") String method);
}
