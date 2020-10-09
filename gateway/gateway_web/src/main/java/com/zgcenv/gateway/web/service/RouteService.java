package com.zgcenv.gateway.web.service;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.gateway.GatewayRoute;
import com.zgcenv.gateway.web.provider.GatewayRouteProvider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName RouteService
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/
@Service
public class RouteService {
    private static final Logger logger = LoggerFactory.getLogger(RouteService.class);

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private GatewayRouteProvider gatewayRouteProvider;

    @CreateCache(name = GATEWAY_ROUTES, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

    private Map<String, RouteDefinition> routeDefinitionMaps = new HashMap<>();

    @PostConstruct
    private void loadRouteDefinition() {
        logger.info("loadRouteDefinition, 开始初使化路由");
        Set<String> gatewayKeys = stringRedisTemplate.keys(GATEWAY_ROUTES + "*");
        if (CollectionUtils.isEmpty(gatewayKeys)) {
            logger.info("loadRouteDefinition, 缓存中没有数据");
            Resp<List<GatewayRoute>> resp = gatewayRouteProvider.routeAll();
            List<GatewayRoute> gatewayRoutes = resp.getResult();
            gatewayRoutes.stream().forEach(gatewayRoute ->
                    gatewayRouteCache.put(gatewayRoute.getRouteId(), gatewayRouteToRouteDefinition(gatewayRoute))
            );
            gatewayKeys = stringRedisTemplate.keys(GATEWAY_ROUTES + "*");
            logger.info("全局初使化网关路由成功!{}",gatewayRoutes.size());

        }
        logger.info("预计初使化路由, gatewayKeys：{}", gatewayKeys);
        // 去掉key的前缀
        Set<String> gatewayKeyIds = gatewayKeys.stream().map(key -> {
            return key.replace(GATEWAY_ROUTES, StringUtils.EMPTY);
        }).collect(Collectors.toSet());
        Map<String, RouteDefinition> allRoutes = gatewayRouteCache.getAll(gatewayKeyIds);
        logger.info("gatewayKeys：{}", allRoutes);
        // 以下代码原因是，jetcache将RouteDefinition返序列化后，uri发生变化，未初使化，导致路由异常，以下代码是重新初使化uri
        allRoutes.values().forEach(routeDefinition -> {
            try {
                routeDefinition.setUri(new URI(routeDefinition.getUri().toASCIIString()));
            } catch (URISyntaxException e) {
                logger.error("网关加载RouteDefinition异常：", e);
            }
        });
        routeDefinitionMaps.putAll(allRoutes);
        logger.info("共初使化路由信息：{}", routeDefinitionMaps.size());
    }

    public Collection<RouteDefinition> getRouteDefinitions() {
        logger.info("加载路由信息：{}", routeDefinitionMaps.size());
        return routeDefinitionMaps.values();
    }

    public boolean save(RouteDefinition routeDefinition) {
        routeDefinitionMaps.put(routeDefinition.getId(), routeDefinition);
        logger.info("新增路由1条：{},目前路由共{}条", routeDefinition, routeDefinitionMaps.size());
        return true;
    }

    public boolean delete(String routeId) {
        routeDefinitionMaps.remove(routeId);
        logger.info("删除路由1条：{},目前路由共{}条", routeId, routeDefinitionMaps.size());
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
            logger.error("网关路由对象转换失败", e);
        }
        return routeDefinition;
    }
}
