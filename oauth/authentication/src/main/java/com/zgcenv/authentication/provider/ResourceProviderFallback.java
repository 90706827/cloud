package com.zgcenv.authentication.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class ResourceProviderFallback implements ResourceProvider {
    private static final Logger log = LoggerFactory.getLogger(ResourceProviderFallback.class);
    @Override
    public Resp<List<Resource>> resources() {
        log.error("认证服务启动时加载资源异常！未加载到资源");
        return Resp.success(new ArrayList<>());
    }

    @Override
    public Resp<List<Resource>> resources(String username) {
        log.error("认证服务查询用户异常！查询用户资源为空！");
        return Resp.success(new ArrayList<>());
    }
}
