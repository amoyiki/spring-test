package com.amoyiki.springtest.service;

import com.amoyiki.springtest.entry.User;

/**
 * @author amoyiki
 * @since 2019/3/7
 */
public interface UserService {
    /**
     *
     * 根据用户名查找
     * @author amoyiki
     * @param username
     * @return com.amoyiki.shirotest.entry.User
     */
    User findByUsername(String username);
}
