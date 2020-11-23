package com.zgcenv.gateway.web.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.zgcenv.entity.vo.UserVo;
import com.zgcenv.gateway.web.provider.AuthorizationProvider;
import com.zgcenv.gateway.web.provider.GatewayRouteProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class PermissionService {

    /**
     * 由authentication-client模块提供签权的feign客户端
     */
    @Resource
    private AuthorizationProvider authProvider;
    @Resource
    private GatewayRouteProvider gatewayRouteProvider;

    @Cached(name = "gateway_auth::", key = "#authentication+#method+#url",
            cacheType = CacheType.LOCAL, expire = 10, timeUnit = TimeUnit.SECONDS, localLimit = 10000)
    public boolean permission(String username, String url, String method) {
        return authProvider.permission(username, url, method).getResult();
    }

    public UserVo findUserInfoByUsername(String username){
       return gatewayRouteProvider.findUserInfoByUsername(username).getResult();
    }
}
