package com.zgcenv.authentication.config;

import com.zgcenv.authentication.service.ResourceService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @ClassName LoadResourceDefine
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/
@Order(2147483647)
@Component
class LoadResourceDefine{

    @Resource
    private ResourceService resourceService;

    /**
     *取消返回的bean防止外部出现线程安全问题
     * 2020/5/15
     * @return
     */
    @PostConstruct
    public void resourceConfigAttributes() {
        resourceService.loadResource();
    }

}
