package com.zgcenv.authentication.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ResourceProviderFallback implements ResourceProvider {
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
