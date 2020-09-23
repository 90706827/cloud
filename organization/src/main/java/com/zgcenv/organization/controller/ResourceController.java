package com.zgcenv.organization.controller;

import com.zgcenv.core.context.Resp;
import com.zgcenv.organization.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ResourceController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/

@RestController
@RequestMapping("/resource")
@Api("resource")
@Slf4j
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    @GetMapping(value = "/user/{username}")
    public Resp queryByUsername(@PathVariable String username) {
        log.debug("query with username:{}", username);
        return Resp.success(resourceService.findResourceByUsername(username));
    }

    @ApiOperation(value = "查询所有资源", notes = "查询所有资源信息")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Resp.class)
    )
    @GetMapping(value = "/all")
    public Resp queryAll() {
        return Resp.success(resourceService.findAll());
    }
}
