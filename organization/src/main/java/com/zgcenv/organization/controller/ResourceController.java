package com.zgcenv.organization.controller;

import com.zgcenv.core.context.Resp;
import com.zgcenv.core.context.RespCode;
import com.zgcenv.entity.organization.Resources;
import com.zgcenv.organization.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ResourceController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/

@Api("resource")
@RestController
public class ResourceController {
    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);

    @Resource
    private ResourceService resourceService;

    @GetMapping(value = "/resource/user")
    public Resp queryByUsername(@RequestParam String username) {
        log.debug("query with username:{}", username);
        List<Resources> list = resourceService.findResourceByUsername(username);
        return Resp.success(list);
    }

    @ApiOperation(value = "查询所有资源", notes = "查询所有资源信息")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Resp.class)
    )
    @GetMapping(value = "/resource/all")
    public Resp queryAll() {
        log.debug("query with all");
        return Resp.success(resourceService.findAll());
    }

    @GetMapping(value = "/resource/findResourceByUsername")
    Resp<List<Resources>> findResourceByUsername(@RequestParam String username) {
        if (StringUtils.isEmpty(username)) {
            return Resp.fail(RespCode.JWT_USER_INVALID);
        }
        return Resp.success(resourceService.findResourceByUsername(username));
    }
}
