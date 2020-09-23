package com.zgcenv.gateway.service;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
    private static final Logger logger =  LoggerFactory.getLogger(RouteService.class);

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @CreateCache(name = GATEWAY_ROUTES, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

    private Map<String, RouteDefinition> routeDefinitionMaps = new HashMap<>();

    @PostConstruct
    private void loadRouteDefinition() {
        logger.info("loadRouteDefinition, 开始初使化路由");
        Set<String> gatewayKeys = stringRedisTemplate.keys(GATEWAY_ROUTES + "*");
        if (CollectionUtils.isEmpty(gatewayKeys)) {
            return;
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
}
