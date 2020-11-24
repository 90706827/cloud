package com.zgcenv.organization.controller;

import com.zgcenv.core.context.Resp;
import com.zgcenv.organization.service.GatewayRouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName RouteController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-25
 * @Version 1.0
 **/
@Api("gateway routes")
@RestController
public class RouteController {
    private static final Logger log = LoggerFactory.getLogger(RouteController.class);

    @Resource
    private GatewayRouteService gatewayRoutService;


    @ApiOperation(value = "根据uri获取网关路由", notes = "根据uri获取网关路由信息，简单查询")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Resp.class)
    )
    @RequestMapping(value = "/routes/all",method = RequestMethod.GET)
    public Resp<?> all() {
        return Resp.success(gatewayRoutService.all());
    }

}
