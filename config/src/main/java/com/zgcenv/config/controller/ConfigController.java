package com.zgcenv.config.controller;

import com.zgcenv.config.service.UserService;
import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ConfigController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-8-27
 * @Version 1.0
 **/
@RestController
public class ConfigController {
    private final static Logger logger = LoggerFactory.getLogger(ConfigController.class);


    @Value("${test:cloud}")
    private String test;
    @Resource
    private UserService userService;

    @GetMapping("/test")
    public Resp<?> test(@RequestParam("username") String username) {
        Resp<Users> users = userService.findUserByUsername(username);
        logger.info(users.toString());
        return users;
    }
}