package com.zgcenv.gateway.web.provider;

import com.zgcenv.core.context.Resp;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @ClassName AuthProviderFallback
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/

@Component
public class AuthProviderFallback implements AuthProvider {
    @Override
    public Resp auth(String authentication, String url, String method) {
        return Resp.success(new ArrayList<>());
    }
}
