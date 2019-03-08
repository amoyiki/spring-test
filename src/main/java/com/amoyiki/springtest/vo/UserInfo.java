package com.amoyiki.springtest.vo;
import lombok.Data;
import java.util.Date;
import java.util.Set;

/**
 * @author amoyiki
 * @since 2019/3/7
 */
@Data
public class UserInfo {
    private Long id;       // 用户id
    private String username;   // 登录名，不可改
    private String password;     // 已加密的登录密码
    private String salt;    // 加密盐值
    private Date created;   // 创建时间
    private Date updated;   // 修改时间
    private Set<String> roles;    //用户所有角色值，用于shiro做角色权限的判断
    private Set<String> perms;    //用户所有权限值，用于shiro做资源权限的判断
}
