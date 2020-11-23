package com.zgcenv.authorization.oauth;

import com.zgcenv.authorization.service.UserService;
import com.zgcenv.entity.organization.Roles;
import com.zgcenv.entity.organization.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName UserDetailsService
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-16
 * @Version 1.0
 **/
@Service("userDetailsService")
public class UsernameDetailsService implements UserDetailsService {

    private final static Logger logger = LoggerFactory.getLogger(UsernameDetailsService.class);

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.findUserByUsername(username).getResult();
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                this.obtainGrantedAuthorities(user));
    }
    /**
     * 获得登录者所有角色的权限集合.
     *
     * @param user
     * @return
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities(Users user) {
        List<Roles> roles = userService.queryUserRolesByUserId(user.getId()).getResult();
        logger.info("user:{},roles:{}", user.getUsername(), roles);
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toSet());
    }
}
