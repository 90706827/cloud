package com.zgcenv.config.service;

import com.zgcenv.config.provider.OrganizationProvider;
import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    public Resp<Users> findUserByUsername(String username)
    {

        return organizationProvider.findUserByUsername(username);
    }

}
