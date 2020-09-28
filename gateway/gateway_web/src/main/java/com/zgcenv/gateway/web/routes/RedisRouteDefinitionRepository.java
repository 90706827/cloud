package com.zgcenv.gateway.web.routes;

import com.zgcenv.gateway.web.service.RouteService;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @ClassName RedisRouteDefinitionRepository
 * @Description 用于对路由定义的操作（保存、删除路由定义）
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    @Resource
    private RouteService routeService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        Collection<RouteDefinition> a = routeService.getRouteDefinitions();
        return Flux.fromIterable(a);
    }
    //保存路由定义到内存中
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            routeService.save(routeDefinition);
            return Mono.empty();
        });
    }
    //根据路由id从内存中删除指定路由定义
    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            routeService.delete(id);
            return Mono.empty();
        });
    }
}
