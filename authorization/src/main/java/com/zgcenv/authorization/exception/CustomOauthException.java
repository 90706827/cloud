package com.zgcenv.authorization.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zgcenv.core.context.Resp;
import com.zgcenv.core.context.RespCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @ClassName CustomOauthException
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Getter
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
class CustomOauthException extends OAuth2Exception {

    private Resp resp;

    CustomOauthException(OAuth2Exception oAuth2Exception) {
        super(oAuth2Exception.getSummary(), oAuth2Exception);
        RespCode respCode = RespCode.valueOf(oAuth2Exception.getOAuth2ErrorCode().toUpperCase());
        this.resp = new Resp(false,respCode.getCode(), respCode.getMsg());
    }
}