package com.zgcenv.gateway.web.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.gateway.GatewayRoute;
import com.zgcenv.entity.vo.UserVo;
import org.springframework.beans.factory.annotation.Qualifier;
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

@Qualifier
@Component
public class GatewayRouteProviderFallback implements GatewayRouteProvider {

    @Override
    public Resp<List<GatewayRoute>> routeAll() {
        return Resp.success(new ArrayList<>());
    }

    @Override
    public Resp<UserVo> findUserInfoByUsername(String username) {
        return Resp.success(new UserVo());
    }
}