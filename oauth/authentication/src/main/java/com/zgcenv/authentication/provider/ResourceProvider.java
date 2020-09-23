package com.zgcenv.authentication.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "organization", fallback = ResourceProviderFallback.class,path = "/organization")
public interface ResourceProvider {

    @GetMapping(value = "/resource/all")
    Resp<Set<Resource>> resources();

    @GetMapping(value = "/resource/user/{username}")
    Resp<Set<Resource>> resources(@PathVariable("username") String username);
}
