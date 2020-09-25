package com.zgcenv.gateway.web.filter;

import com.zgcenv.core.context.Resp;
import com.zgcenv.core.context.RespCode;
import com.zgcenv.core.jackson.JsonUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName IPCheckFilter
 * @Description IP 白名单
 * @Author Mr.Jangni
 * @Date 2020-9-10
 * @Version 1.0
 **/
//@Component
public class IPCheckFilter implements GlobalFilter, Ordered {

        @Override
        public int getOrder() {
            return 0;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            // 此处写死了，演示用，实际中需要采取配置的方式
            if (getIp(headers).equals("127.0.0.1")) {
                ServerHttpResponse response = exchange.getResponse();
                Resp resp = Resp.fail(RespCode.IP_CHECK);
                response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                DataBuffer dataBuffer = response.bufferFactory().wrap(JsonUtil.toJsonAsBytes(resp));
                return response.writeWith(Mono.just(dataBuffer));
            }
            return chain.filter(exchange);
        }

        // 这边从请求头中获取用户的实际IP,根据Nginx转发的请求头获取
        private String getIp(HttpHeaders headers) {
            return "127.0.0.2";
        }
}

