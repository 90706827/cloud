package com.zgcenv.gateway.web.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.core.context.RespCode;
import org.springframework.stereotype.Component;

/**
 * @ClassName AuthProviderFallback
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/

@Component
public class AuthProviderFallback implements AuthProvider {
    /**
     * 降级统一返回无权限
     */
    @Override
    public Resp auth(String authentication, String url, String method) {
        return Resp.fail(RespCode.SYSTEM_BUSY);
    }
}
