package com.zgcenv.authorization.service;

import com.zgcenv.authorization.provider.OrganizationProvider;
import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Roles;
import com.zgcenv.entity.organization.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private OrganizationProvider organizationProvider;

    public Resp<Users> findUserByUsername(String username) {
        return organizationProvider.findUserByUsername(username);
    }

    public Resp<List<Roles>> queryUserRolesByUserId(Long userId) {
        return organizationProvider.queryUserRolesByUserId(userId);
    }
    public Resp<Users> findUserByMobile( String mobile){
        return organizationProvider.findUserByMobile(mobile);
    }

}
