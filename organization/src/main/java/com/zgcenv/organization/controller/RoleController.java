package com.zgcenv.organization.controller;

import com.zgcenv.core.context.Resp;
import com.zgcenv.organization.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api("Role")
public class RoleController {
    @Resource
    private RoleService roleService;


    @ApiOperation(value = "获取用户角色", notes = "获取用户角色")
    @GetMapping("/role/findUserRolesByUserId")
    public Resp<?> findUserRolesByUserId(@RequestParam Long userId) {
        return Resp.success(roleService.findRolesById(userId));
    }
}
