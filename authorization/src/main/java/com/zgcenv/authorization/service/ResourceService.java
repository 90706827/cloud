package com.zgcenv.authorization.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.zgcenv.authorization.provider.OrganizationProvider;
import com.zgcenv.authorization.utils.NewMvcRequestMatcher;
import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResourceService {
    private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);

    @Resource
    private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    @Resource
    private OrganizationProvider organizationProvider;

    /**
     * 系统中所有权限集合
     */
    private static final Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes = new HashMap<>();

    public synchronized void saveResource(Resources resource) {
        resourceConfigAttributes.put(
                this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                new SecurityConfig(resource.getCode())
        );
        logger.info("resourceConfigAttributes size:{}", resourceConfigAttributes.size());
    }

    public synchronized void removeResource(Resources resource) {
        resourceConfigAttributes.remove(this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()));
        logger.info("resourceConfigAttributes size:{}", resourceConfigAttributes.size());
    }

    public synchronized void loadResource() {
        Resp<List<Resources>> resourcesResult = organizationProvider.resourceAll();
        if (resourcesResult.getSuccess()) {
            System.exit(1);
        }
        List<Resources> resources = resourcesResult.getResult();
        Map<MvcRequestMatcher, SecurityConfig> tempResourceConfigAttributes = resources.stream()
                .collect(Collectors.toMap(
                        resource -> this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                        resource -> new SecurityConfig(resource.getCode())
                ));
        resourceConfigAttributes.putAll(tempResourceConfigAttributes);
        logger.debug("init resourceConfigAttributes:{}", resourceConfigAttributes);
    }

    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest) {
        return resourceConfigAttributes.keySet().stream()
                .filter(requestMatcher -> requestMatcher.matches(authRequest))
                .map(requestMatcher -> resourceConfigAttributes.get(requestMatcher))
                .peek(urlConfigAttribute -> logger.debug("url在资源池中配置：{}", urlConfigAttribute.getAttribute()))
                .findFirst()
                .orElse(new SecurityConfig("NONEXISTENT_URL"));
    }

    @Cached(name = "resource4user::", key = "#username", cacheType = CacheType.LOCAL)
    public List<Resources> queryByUsername(String username) {
        return organizationProvider.findResourceByUsername(username).getResult();
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
