package com.amoyiki.springtest.shiro;

import com.amoyiki.springtest.entry.User;
import com.amoyiki.springtest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @description:
 * @author: amoyiki
 * @date: 2019/2/28
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;


    /**
     * 授权- 角色权限校验
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证- 登录校验
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = (String) token.getCredentials();
        log.info("用户 {}  认证-------ShiroRealm.doGetAuthenticationInfo", username);

        // 查找用户

        Optional<User> op = userRepository.findByUsername(username);
        if (!op.isPresent()) {
            throw new UnknownAccountException("用户密码错误!");
        }
        User user = op.get();
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名密码错误!");
        }
        if (!user.getStatus().equals("0")){
            throw new LockedAccountException("账号已被锁定，请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
