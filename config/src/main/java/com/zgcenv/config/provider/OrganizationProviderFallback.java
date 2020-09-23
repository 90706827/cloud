package com.zgcenv.config.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.core.context.RespCode;
import com.zgcenv.entity.organization.Users;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrganizationProviderFallback
 * @Description 熔断策略
 * @Author Mr.Jangni
 * @Date 2020-9-16
 * @Version 1.0
 **/
@Component
public class OrganizationProviderFallback implements OrganizationProvider {

    @Override
    public Resp<Users> findUserByUsername(String username) {
        //当请求微服务出现网络问题时出发熔断机制，熔断处理逻辑如下
        return Resp.fail(RespCode.SYSTEM_BUSY);
    }

}