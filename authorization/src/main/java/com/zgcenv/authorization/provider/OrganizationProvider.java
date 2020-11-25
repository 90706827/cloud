package com.zgcenv.authorization.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.entity.organization.Resources;
import com.zgcenv.entity.organization.Roles;
import com.zgcenv.entity.organization.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName OrganizationProvider
 * @Description 熔断机制接口
 * @Author Mr.Jangni
 * @Date 2020-9-16
 * @Version 1.0
 **/
@FeignClient(name = "organization", fallback = OrganizationProviderFallback.class)
public interface OrganizationProvider {

    @GetMapping(value = "/user/findUserByUsername")
    Resp<Users> findUserByUsername(@RequestParam("username") String username);

    @GetMapping(value = "/user/findUserByMobile")
    Resp<Users> findUserByMobile(@RequestParam("mobile") String mobile);

    @GetMapping(value = "/role/findUserRolesByUserId")
    Resp<List<Roles>> queryUserRolesByUserId(@RequestParam("userId") Long userId);

    @GetMapping(value = "/resource/all")
    Resp<List<Resources>> resourceAll();

    @GetMapping(value = "/resource/findResourceByUsername")
    Resp<List<Resources>> findResourceByUsername(@RequestParam("username")String username);

}

