package com.zgcenv.authorization.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Resources;
import com.zgcenv.entity.organization.Roles;
import com.zgcenv.entity.organization.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrganizationProviderFallback
 * @Description 熔断策略
 * @Author Mr.Jangni
 * @Date 2020-9-16
 * @Version 1.0
 **/
@Component
public class OrganizationProviderFallback implements OrganizationProvider {

    //当请求微服务出现网络问题时出发熔断机制，熔断处理逻辑如下

    @Override
    public Resp<Users> findUserByUsername(String username) {
        return Resp.success(new Users());
    }

    @Override
    public Resp<Users> findUserByMobile(String mobile) {
        return Resp.success(new Users());
    }

    @Override
    public Resp<List<Roles>> queryUserRolesByUserId(Long userId) {
        return Resp.success(new ArrayList<>());
    }

    @Override
    public Resp<List<Resources>> resourceAll() {
        return Resp.success(new ArrayList<>());
    }

    @Override
    public Resp<List<Resources>> findResourceByUsername(String username) {
        return Resp.success(new ArrayList<>());
    }

}