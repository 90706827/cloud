package com.zgcenv.authorization.oauth.enhancer;

import com.google.common.collect.Maps;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * @ClassName CustomTokenEnhancer
 * @Description 自定义token携带内容
 * @Author Mr.Jangni
 * @Date 2020-9-21
 * @Version 1.0
 **/
public class CustomTokenEnhancer implements TokenEnhancer {
    /**
     * 客户端模式
     */
    private final static String CLIENT_CREDENTIALS = "client_credentials";

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        //客户端模式不进行增强
        if (CLIENT_CREDENTIALS.equals(authentication.getOAuth2Request().getGrantType())) {
            return accessToken;
        }
        //获取要增强携带的字段
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();

        Map<String, Object> additionalInfo = Maps.newHashMap();
        //自定义token内容，加入组织机构信息
        additionalInfo.put("organization", authentication.getName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
