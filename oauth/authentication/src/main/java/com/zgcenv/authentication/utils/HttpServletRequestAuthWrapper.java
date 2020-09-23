package com.zgcenv.authentication.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @ClassName HttpServletRequestAuthWrapper
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-23
 * @Version 1.0
 **/
public class HttpServletRequestAuthWrapper extends HttpServletRequestWrapper {

    private String url;
    private String method;

    /**
     * @param url
     * @param method
     */
    public HttpServletRequestAuthWrapper(HttpServletRequest request, String url, String method) {
        super(request);
        this.url = url;
        this.method = method;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public String getServletPath() {
        return this.url;
    }
}

