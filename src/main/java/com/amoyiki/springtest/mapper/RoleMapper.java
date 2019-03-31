package com.amoyiki.springtest.mapper;

import com.amoyiki.springtest.baseMapper.BaseMapper;
import com.amoyiki.springtest.entry.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<Role> findRoleListByUid(Integer id);
}