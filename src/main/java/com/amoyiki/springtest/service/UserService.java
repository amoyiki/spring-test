package com.amoyiki.springtest.service;

import com.amoyiki.springtest.entry.User;
import com.amoyiki.springtest.vo.UserInfo;

/**
 * @author amoyiki
 * @date 2019/3/7
 */
public interface UserService {

    /**
     * 根据用户名查找
     *
     * @author amoyiki
     * @param username
     * @return com.amoyiki.springtest.vo.UserInfo
     */
    UserInfo findByUsername(String username);
}
