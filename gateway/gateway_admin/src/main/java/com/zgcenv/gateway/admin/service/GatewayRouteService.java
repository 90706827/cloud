package com.zgcenv.gateway.admin.service;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgcenv.entity.gateway.GatewayRoute;
import com.zgcenv.gateway.admin.config.BusConfig;
import com.zgcenv.gateway.admin.config.EventSender;
import com.zgcenv.gateway.admin.dao.GatewayRouteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * @ClassName GatewayRouteService
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-25
 * @Version 1.0
 **/
@Service

public class GatewayRouteService {
    private static final Logger log = LoggerFactory.getLogger(GatewayRouteService.class);

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @CreateCache(name = GATEWAY_ROUTES, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

//    @Resource
//    private EventSender eventSender;

    @Resource
    private GatewayRouteDao gatewayRouteDao;

    public boolean add(GatewayRoute gatewayRoute) {
        gatewayRouteDao.save(gatewayRoute);
        // 转化为gateway需要的类型，缓存到redis, 并事件通知各网关应用
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        gatewayRouteCache.put(gatewayRoute.getRouteId(), routeDefinition);
//        eventSender.send(BusConfig.ROUTING_KEY, routeDefinition);
        return true;
    }

    public boolean delete(Long id) {
        GatewayRoute gatewayRoute = gatewayRouteDao.getOne(id);
        // 删除redis缓存, 并事件通知各网关应用
        gatewayRouteCache.remove(gatewayRoute.getRouteId());
//        eventSender.send(BusConfig.ROUTING_KEY, gatewayRouteToRouteDefinition(gatewayRoute));
        gatewayRouteDao.deleteById(id);
        return true;
    }

    public boolean update(GatewayRoute gatewayRoute) {
        gatewayRouteDao.save(gatewayRoute);
        // 转化为gateway需要的类型，缓存到redis, 并事件通知各网关应用
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(gatewayRoute);
        gatewayRouteCache.put(gatewayRoute.getRouteId(), routeDefinition);
//        eventSender.send(BusConfig.ROUTING_KEY, routeDefinition);
        return true;
    }

    /**
     * 将数据库中json对象转换为网关需要的RouteDefinition对象
     *
     * @param gatewayRoute
     * @return RouteDefinition
     */
    private RouteDefinition gatewayRouteToRouteDefinition(GatewayRoute gatewayRoute) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRoute.getRouteId());
        routeDefinition.setOrder(gatewayRoute.getOrders());
        routeDefinition.setUri(URI.create(gatewayRoute.getUri()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            routeDefinition.setFilters(objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {
            }));
            routeDefinition.setPredicates(objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {
            }));
        } catch (IOException e) {
            log.error("网关路由对象转换失败", e);
        }
        return routeDefinition;
    }

    public GatewayRoute get(Long id) {
        return gatewayRouteDao.getOne(id);
    }

    public List<GatewayRoute> queryRouteByUri(String uri){
        return gatewayRouteDao.findAllByUriLike(uri);
    }

    @PostConstruct
    public boolean overload() {
        List<GatewayRoute> gatewayRoutes = gatewayRouteDao.findAll();
        gatewayRoutes.forEach(gatewayRoute ->
                gatewayRouteCache.put(gatewayRoute.getRouteId(), gatewayRouteToRouteDefinition(gatewayRoute))
        );
        log.info("全局初使化网关路由成功!");
        return true;
    }
}
