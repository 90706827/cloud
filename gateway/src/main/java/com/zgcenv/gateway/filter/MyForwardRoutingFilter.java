package com.zgcenv.gateway.filter;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.ForwardRoutingFilter;
import org.springframework.web.reactive.DispatcherHandler;

/**
 * @ClassName MyForwardRoutingFilter
 * @Description 会查看exchange的属性
 * @Author Mr.Jangni
 * @Date 2020-9-11
 * @Version 1.0
 **/
public class MyForwardRoutingFilter extends ForwardRoutingFilter {

    public MyForwardRoutingFilter(ObjectProvider<DispatcherHandler> dispatcherHandlerProvider) {
        super(dispatcherHandlerProvider);
    }
}
