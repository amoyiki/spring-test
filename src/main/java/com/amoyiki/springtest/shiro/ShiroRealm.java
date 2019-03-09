package com.amoyiki.springtest.shiro;
import com.amoyiki.springtest.entry.User;
import com.amoyiki.springtest.service.UserService;
import com.amoyiki.springtest.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;


/**
 * 自定义权限匹配和账号密码匹配
 * @author amoyiki
 * @since  2019/2/28
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;
    /**
     *  授权- 角色权限校验
     *  获取用户角色和权限，给 shiro 做权限判断
     * @author amoyiki
     * @param principals 认证信息
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("├ [权限校验]: 用户角色权限校验");
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        log.info("├ [角色代码]: {}",userInfo);
        // 有值情况
        if (userInfo != null && !CollectionUtils.isEmpty(userInfo.getRoles())) {
            for (String role : userInfo.getRoles()) {
                authorizationInfo.addRole(role);
                log.info("├ [角色代码]: {}",role);
            }
            for (String perm : userInfo.getPerms()) {
                authorizationInfo.addStringPermission(perm);
                log.info("├ [权限代码]: {}",perm);
            }
        }
        return authorizationInfo;
    }

    /**
     * 认证- 登录校验
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("├ [登录校验]: 用户账号及密码验证, {}",token.getPrincipal());
        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        // 查找用户
        UserInfo user = userService.findByUsername(username);

        if (user == null) {
            throw new UnknownAccountException("用户密码错误!");
        }else if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名密码错误!");
        }else if (user.getStatus().equals("0")){
            throw new LockedAccountException("账号已被锁定，请联系管理员");
        }
        // 获取到用户后加入权限和角色信息

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
