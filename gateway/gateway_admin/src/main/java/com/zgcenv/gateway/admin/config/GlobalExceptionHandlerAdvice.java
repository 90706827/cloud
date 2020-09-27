package com.zgcenv.gateway.admin.config;

import com.zgcenv.core.context.Resp;
import com.zgcenv.core.context.RespCode;
import com.zgcenv.core.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice

public class GlobalExceptionHandlerAdvice {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public Resp<?> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.info("missing servlet request parameter exception:{}", ex.getMessage());
        return Resp.fail(RespCode.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {MultipartException.class})
    public Resp<?> uploadFileLimitException(MultipartException ex) {
        log.error("upload file size limit:{}", ex.getMessage());
        return Resp.fail(RespCode.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Resp<?> serviceException(MethodArgumentNotValidException ex) {
        log.error("service exception:{}", ex.getMessage());
        return Resp.fail(RespCode.ARGUMENT_NOT_VALID);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public Resp<?> duplicateKeyException(DuplicateKeyException ex) {
        log.error("primary key duplication exception:{}", ex.getMessage());
        return Resp.fail(RespCode.DUPLICATE_PRIMARY_KEY);
    }

    @ExceptionHandler(value = {BizException.class})
    public Resp<?> baseException(BizException ex) {
        log.error("base exception:{}", ex.getMessage());
        return Resp.fail(ex.getRespCode());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Resp<?> exception() {
        return Resp.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Resp<?> throwable() {
        return Resp.fail();
    }
}