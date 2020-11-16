package com.zgcenv.organization.service;

import com.zgcenv.entity.organization.Roles;
import com.zgcenv.organization.dao.RoleDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private RoleDao roleDao;

    public List<Roles> findRolesById(Long userId) {
        return findRolesById(userId);
    }
}
