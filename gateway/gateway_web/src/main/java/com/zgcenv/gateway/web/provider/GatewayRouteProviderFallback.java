package com.zgcenv.gateway.web.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.gateway.GatewayRoute;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GatewayRouteProviderFallback
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-28
 * @Version 1.0
 **/
@Component
public class GatewayRouteProviderFallback implements GatewayRouteProvider {

    @Override
    public Resp<List<GatewayRoute>> routeAll() {
        return Resp.success(new ArrayList<GatewayRoute>());
    }
}