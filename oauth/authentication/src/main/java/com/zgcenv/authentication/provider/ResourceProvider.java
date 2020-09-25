package com.zgcenv.authentication.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "organization", fallback = ResourceProviderFallback.class, path = "/organization")
public interface ResourceProvider {

    @GetMapping(value = "/resource/all")
    Resp<List<Resource>> resources();

    @GetMapping(value = "/resource/user")
    Resp<List<Resource>> resources(@RequestParam("username") String username);
}
