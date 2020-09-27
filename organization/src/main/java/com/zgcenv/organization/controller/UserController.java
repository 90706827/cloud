package com.zgcenv.organization.controller;

import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Users;
import com.zgcenv.organization.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-16
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
@Api("user")

public class UserController {
    @Resource
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "获取用户", notes = "根据用户名称获取用户信息")
    @ApiImplicitParam(paramType = "query", name = "username", value = "用户名称", required = true, dataType = "string")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Resp.class))
    @GetMapping("/findUserByUsername")
    public Resp<Users> findUserByUsername(@RequestParam String username) {
        logger.info("query with username or mobile:{}", username);
        return Resp.success(userService.findUserByUsername(username));
    }

}