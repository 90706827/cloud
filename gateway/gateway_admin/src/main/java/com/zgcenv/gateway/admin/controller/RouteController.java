package com.zgcenv.gateway.admin.controller;

import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.gateway.GatewayRoute;
import com.zgcenv.gateway.admin.entity.GatewayRouteForm;
import com.zgcenv.gateway.admin.service.GatewayRouteService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName RouteController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-25
 * @Version 1.0
 **/

@RestController
@RequestMapping("/gateway/routes")
@Api("gateway routes")

public class RouteController {
    private static final Logger log = LoggerFactory.getLogger(RouteController.class);
    @Resource
    private GatewayRouteService gatewayRoutService;

    @ApiOperation(value = "新增网关路由", notes = "新增一个网关路由")
    @ApiImplicitParam(name = "gatewayRoutForm",
            value = "新增网关路由form表单",
            required = true,
            dataType = "GatewayRouteForm")
    @PostMapping
    public Resp<?> add(@Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        log.info("name:", gatewayRoutForm);
        GatewayRoute gatewayRout = gatewayRoutForm.toPo(GatewayRoute.class);
        return Resp.success(gatewayRoutService.add(gatewayRout));
    }

    @ApiOperation(value = "删除网关路由", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "string")
    @DeleteMapping(value = "/{id}")
    public Resp<?> delete(@PathVariable Long id) {
        return Resp.success(gatewayRoutService.delete(id));
    }

    @ApiOperation(value = "修改网关路由", notes = "修改指定网关路由信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "网关路由ID", required = true, dataType = "string"),
            @ApiImplicitParam(name = "gatewayRoutForm", value = "网关路由实体", required = true, dataType = "GatewayRouteForm")
    })
    @PutMapping(value = "/{id}")
    public Resp<?> update(@PathVariable String id, @Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        GatewayRoute gatewayRout = gatewayRoutForm.toPo(GatewayRoute.class);
        return Resp.success(gatewayRoutService.update(gatewayRout));
    }

    @ApiOperation(value = "获取网关路由", notes = "根据id获取指定网关路由信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public Resp<?> get(@PathVariable Long id) {
        log.info("get with id:{}", id);
        return Resp.success(gatewayRoutService.get(id));
    }

    @ApiOperation(value = "根据uri获取网关路由", notes = "根据uri获取网关路由信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "name", value = "网关路由路径", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Resp.class)
    )
    @GetMapping
    public Resp<?> getByUri(@RequestParam String uri) {
        return Resp.success(gatewayRoutService.queryRouteByUri(uri));
    }


    @ApiOperation(value = "重载网关路由", notes = "将所以网关的路由全部重载到redis中")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Resp.class)
    )
    @PostMapping(value = "/overload")
    public Resp<?> overload() {
        return Resp.success(gatewayRoutService.overload());
    }

}
