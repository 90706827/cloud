package com.zgcenv.organization.service;

import com.zgcenv.entity.organization.Users;
import com.zgcenv.organization.dao.UsersDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName UserService
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-16
 * @Version 1.0
 **/
@Service
public class UserService {

    @Resource
    private UsersDao usersDao;

    public Users findUserByUsername(String username) {
        return usersDao.findByUsername(username);
    }

}
