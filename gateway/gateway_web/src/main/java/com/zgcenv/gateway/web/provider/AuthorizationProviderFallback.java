package com.zgcenv.gateway.web.provider;

import com.zgcenv.core.context.Resp;
import org.springframework.stereotype.Component;

/**
 * @ClassName AuthProviderFallback
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/

@Component
public class AuthorizationProviderFallback implements AuthorizationProvider {

    @Override
    public Resp<Boolean> permission(String authentication, String url, String method) {
        return Resp.success(Boolean.FALSE);
    }
}
