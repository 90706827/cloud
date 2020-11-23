package com.zgcenv.authorization.oauth;

import com.zgcenv.authorization.service.UserService;
import com.zgcenv.entity.organization.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @ClassName MobileDetailsService
 * @Description 手机登录
 * @Author Mr.Jangni
 * @Date 2020-9-21
 * @Version 1.0
 **/
@Service("mobileUserDetailsService")
public class MobileDetailsService extends UsernameDetailsService {

    private final static Logger logger = LoggerFactory.getLogger(MobileDetailsService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String mobile) {

        Users user = userService.findUserByMobile(mobile).getResult();
        logger.info("load user by mobile:{}", user.toString());

        // 如果为mobile模式，从短信服务中获取验证码（动态密码）
        String credentials = "123456";

        return new User(
                user.getUsername(),
                credentials,
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                super.obtainGrantedAuthorities(user));
    }
}
