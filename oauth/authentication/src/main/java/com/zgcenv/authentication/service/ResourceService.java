package com.zgcenv.authentication.service;

import com.zgcenv.authentication.provider.ResourceProvider;
import com.zgcenv.authentication.utils.NewMvcRequestMatcher;
import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName ResourceService
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/

@Service
@Slf4j
public class ResourceService {

    @javax.annotation.Resource
    private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    @javax.annotation.Resource
    private ResourceProvider resourceProvider;

    /**
     * 系统中所有权限集合
     */
    private static final Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes = new HashMap<>();

    public synchronized void saveResource(Resource resource) {
        resourceConfigAttributes.put(
                this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                new SecurityConfig(resource.getCode())
        );
        log.info("resourceConfigAttributes size:{}", resourceConfigAttributes.size());
    }

    public synchronized void removeResource(Resource resource) {
        resourceConfigAttributes.remove(this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()));
        log.info("resourceConfigAttributes size:{}", resourceConfigAttributes.size());
    }

    public synchronized void loadResource() {
        Resp<Set<Resource>> resourcesResult = resourceProvider.resources();
        if (resourcesResult.isSuccess()) {
            System.exit(1);
        }
        Set<Resource> resources = resourcesResult.getResult();
        Map<MvcRequestMatcher, SecurityConfig> tempResourceConfigAttributes = resources.stream()
                .collect(Collectors.toMap(
                        resource -> this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                        resource -> new SecurityConfig(resource.getCode())
                ));
        resourceConfigAttributes.putAll(tempResourceConfigAttributes);
        log.debug("init resourceConfigAttributes:{}", resourceConfigAttributes);
    }

    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest) {
        return resourceConfigAttributes.keySet().stream()
                .filter(requestMatcher -> requestMatcher.matches(authRequest))
                .map(requestMatcher -> resourceConfigAttributes.get(requestMatcher))
                .peek(urlConfigAttribute -> log.debug("url在资源池中配置：{}", urlConfigAttribute.getAttribute()))
                .findFirst()
                .orElse(new SecurityConfig("NONEXISTENT_URL"));
    }

//    @Cached(name = "resource4user::", key = "#username", cacheType = CacheType.LOCAL)
    public Set<Resource> queryByUsername(String username) {
        return resourceProvider.resources(username).getResult();
    }

    /**
     * 创建RequestMatcher
     *
     * @param url
     * @param method
     * @return
     */
    private MvcRequestMatcher newMvcRequestMatcher(String url, String method) {
        return new NewMvcRequestMatcher(mvcHandlerMappingIntrospector, url, method);
    }
}
