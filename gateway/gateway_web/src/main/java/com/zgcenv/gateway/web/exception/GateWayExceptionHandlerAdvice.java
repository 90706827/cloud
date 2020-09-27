package com.zgcenv.gateway.web.exception;

import com.zgcenv.core.context.Resp;
import com.zgcenv.core.context.RespCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.netty.channel.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


@Component
public class GateWayExceptionHandlerAdvice {

    private static final Logger log = LoggerFactory.getLogger(GateWayExceptionHandlerAdvice.class);
    @ExceptionHandler(value = {ResponseStatusException.class})
    public Resp<?> handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        return Resp.fail(RespCode.GATEWAY_ERROR);
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public Resp<?> handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        return Resp.fail(RespCode.GATEWAY_CONNECT_TIME_OUT);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Resp<?> handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        return Resp.fail(RespCode.GATEWAY_NOT_FOUND_SERVICE);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Resp<?> handle(ExpiredJwtException ex) {
        log.error("ExpiredJwtException:{}", ex.getMessage());
        return Resp.fail(RespCode.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {SignatureException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Resp<?> handle(SignatureException ex) {
        log.error("SignatureException:{}", ex.getMessage());
        return Resp.fail(RespCode.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {MalformedJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Resp<?> handle(MalformedJwtException ex) {
        log.error("MalformedJwtException:{}", ex.getMessage());
        return Resp.fail(RespCode.INVALID_TOKEN);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Resp<?> handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return Resp.fail();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Resp<?> handle(Exception ex) {
        log.error("exception:{}", ex.getMessage());
        return Resp.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Resp<?> handle(Throwable throwable) {
        if (throwable instanceof ResponseStatusException) {
            return handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            return handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            return handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            return handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            return handle((Exception) throwable);
        } else {
            return Resp.fail();
        }
    }
}
