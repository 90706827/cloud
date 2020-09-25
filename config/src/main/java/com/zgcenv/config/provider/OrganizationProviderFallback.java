package com.zgcenv.config.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.core.context.RespCode;
import com.zgcenv.entity.organization.Resource;
import com.zgcenv.entity.organization.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName OrganizationProviderFallback
 * @Description 熔断策略
 * @Author Mr.Jangni
 * @Date 2020-9-16
 * @Version 1.0
 **/
@Component
@Slf4j
public class OrganizationProviderFallback implements OrganizationProvider {

    @Override
    public Resp<Users> findUserByUsername(String username) {
        //当请求微服务出现网络问题时出发熔断机制，熔断处理逻辑如下
        return Resp.fail(RespCode.SYSTEM_BUSY);
    }
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