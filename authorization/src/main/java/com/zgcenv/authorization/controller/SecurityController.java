package com.zgcenv.authorization.controller;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.zgcenv.authorization.service.AuthenticationService;
import com.zgcenv.authorization.utils.HttpServletRequestAuthWrapper;
import com.zgcenv.core.context.Resp;
import com.zgcenv.core.context.RespCode;
import com.zgcenv.core.exception.BizException;
import com.zgcenv.core.redis.RedisService;
import com.zgcenv.entity.utils.IdUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SecurityController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-14
 * @Version 1.0
 **/
@RestController
public class SecurityController {

    @Resource
    private ConsumerTokenServices consumerTokenServices;
    @Resource
    private AuthenticationService authenticationService;
    @Resource
    private TokenEndpoint tokenEndpoint;
    @Resource
    private RedisService redisService;
    @Resource
    private IdUtils idUtils;

    @DeleteMapping("signout")
    public Object signout(HttpServletRequest request) throws BizException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");

        if (!consumerTokenServices.revokeToken(token)) {
            return Resp.fail(RespCode.BAD_REQUEST);
        }
        return Resp.success("退出登录成功");
    }

    @ApiOperation(value = "权限验证", notes = "根据用户token，访问的url和method判断用户是否有权限访问")
    @RequestMapping(value = "/auth/permission", method = RequestMethod.POST)
    public Resp<?> decide(HttpServletRequest request, @RequestParam String url, @RequestParam String method) {
        boolean decide = authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
        return Resp.success(decide);
    }

    //@RequestParam Map<String, String> parameters
    @ApiOperation(value = "登录/oauth/token", notes = "重写/oauth/token请求，同意返回")
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
    public Resp<?> token(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        ResponseEntity<OAuth2AccessToken> responseEntity = tokenEndpoint.postAccessToken(principal, parameters);
        return Resp.success(responseEntity.getBody());
    }
//    /authorization/oauth/captcha
    @RequestMapping(value = "/oauth/captcha", method = RequestMethod.GET)
    public Resp<?> captcha(HttpServletRequest request, HttpServletResponse response) throws HttpRequestMethodNotSupportedException {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setCharType(Captcha.FONT_5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = idUtils.randomUUID();
        // 存入redis并设置过期时间为30分钟
        redisService.set(key, verCode, 30, TimeUnit.MINUTES);
        // 将key和base64返回给前端
//        put("key", key).put("image", specCaptcha.toBase64());
        Map<String, String> map = new HashMap();
        map.put("key", key);
        map.put("image", specCaptcha.toBase64());
        return Resp.success(map);
    }

}
