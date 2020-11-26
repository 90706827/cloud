package com.zgcenv.gateway.web.filter;

import com.zgcenv.core.context.Resp;
import com.zgcenv.core.context.RespCode;
import com.zgcenv.core.jackson.JsonUtil;
import com.zgcenv.entity.vo.UserVo;
import com.zgcenv.gateway.web.service.PermissionService;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.stream.Stream;

/**
 * 请求url权限校验
 */
@Configuration
@ComponentScan(basePackages = "com.zgcenv.gateway")
public class AccessGatewayFilter implements GlobalFilter {
    private static final Logger logger = LoggerFactory.getLogger(AccessGatewayFilter.class);

    /**
     * Authorization认证开头是"bearer "
     */
    private static final String BEARER = "Bearer ";
    private static final String X_CLIENT_TOKEN_USER = "x-client-token-user";
    private static final String X_CLIENT_TOKEN = "x-client-token";

    /**
     * jwt token 密钥，主要用于token解析，签名验证
     */
    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;
    /**
     * 由authentication-client模块提供签权的feign客户端
     */

    @Resource
    private PermissionService permissionService;

    /**
     * 1.首先网关检查token是否有效，无效直接返回401，不调用签权服务
     * 2.调用签权服务器看是否对该请求有权限，有权限进入下一个filter，没有权限返回401
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String url = request.getPath().value();
        logger.info("url:{},method:{},headers:{}", url, method, request.getHeaders());

        //不需要网关签权的url
        if (ignoreAuthentication(url)) {
            logger.info("路由拦截器，自定义放行：{}", url);
            return chain.filter(exchange);
        }
        logger.info(String.valueOf(authentication.indexOf(BEARER)));
        // 测试环境放行token验证
        if (StringUtils.isEmpty(authentication) || authentication.indexOf(BEARER) == 0) {
            logger.info("Token为空 或者 Token存在放行：{}", url);
            return chain.filter(exchange);
        }
        if (StringUtils.isEmpty(authentication) || !authentication.startsWith(BEARER)) {
            logger.error("user token is null or error");
            return unauthorized(exchange, RespCode.JWT_BASIC_INVALID);
        }
        Jws<Claims> jws = null;
        try {
            jws = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(signingKey.getBytes()) //设置签名的秘钥
                    .parseClaimsJws(StringUtils.substring(authentication, BEARER.length()));
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException ex) {
            logger.error(ex.getMessage());
        }
        if (jws != null && permissionService.permission(jws.getBody().get("user_name").toString(), url, method)) {
            ServerHttpRequest.Builder builder = request.mutate();
            //TODO 转发的请求都加上服务间认证token
            builder.header(X_CLIENT_TOKEN, "TODO 添加服务间简单认证");
            //将jwt token中的用户信息传给服务
            builder.header(X_CLIENT_TOKEN_USER, "TODO 服务间认证token");
            if ("POST".equals(method) || "DELETE".equals(method)) {
                UserVo userVo = permissionService.findUserInfoByUsername(jws.getBody().get("user_name").toString());
                if (ObjectUtils.isEmpty(userVo)) {
                    logger.error("user not found!");
                    return unauthorized(exchange, RespCode.JWT_BASIC_INVALID);
                }
                builder.header("userId", userVo.getId().toString());
            }
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }
        return unauthorized(exchange, RespCode.UN_AUTHORIZED);

    }

    private boolean ignoreAuthentication(String url) {

        return Stream.of("/oauth,/open,/v2/api-docs".split(",")).anyMatch(ignoreUrl ->
                url.startsWith(StringUtils.trim(ignoreUrl)));
//        return Stream.of("/oauth,/v2/api-docs".split(",")).anyMatch(ignoreUrl ->
//                url.substring(url.indexOf("/", 1), url.length()).startsWith(ignoreUrl.trim()));
    }

    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange, RespCode respCode) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(JsonUtil.toJsonAsBytes(Resp.fail(respCode)));
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

}
