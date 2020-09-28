package com.zgcenv.config.service;

import com.zgcenv.config.provider.OrganizationProvider;
import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.gateway.GatewayRoute;
import com.zgcenv.entity.organization.Resource;
import com.zgcenv.entity.organization.Users;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserService
 * @Description UserService
 * @Author Mr.Jangni
 * @Date 2020-9-16
 * @Version 1.0
 **/
@Service
public class UserService {

    @javax.annotation.Resource
    private OrganizationProvider organizationProvider;

    public Resp<Users> findUserByUsername(String username)
    {

        return organizationProvider.findUserByUsername(username);
    }

    public Resp<List<Resource>> getAll(){
        return organizationProvider.resources();
    }
    public Resp<List<GatewayRoute>> getRouteAll(){
        Resp<List<GatewayRoute>> resp = organizationProvider.routeAll();
        return resp;
    }

    public Resp<List<Resource>> getUser(String username){
        return organizationProvider.resources(username);
    }
}
