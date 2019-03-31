package com.amoyiki.springtest.mapper;

import com.amoyiki.springtest.baseMapper.BaseMapper;
import com.amoyiki.springtest.entry.User;

public interface UserMapper extends BaseMapper<User> {

    User findByUsername(String username);
}