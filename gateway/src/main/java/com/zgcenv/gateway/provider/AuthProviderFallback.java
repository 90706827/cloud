package com.zgcenv.gateway.provider;

import com.zgcenv.core.context.Resp;
import com.zgcenv.core.context.RespCode;

/**
 * @ClassName AuthProviderFallback
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/
public class AuthProviderFallback implements AuthProvider {
    /**
     * 降级统一返回无权限
     */
    @Override
    public Resp auth(String authentication, String url, String method) {
        return Resp.fail(RespCode.SYSTEM_BUSY);
    }
}
