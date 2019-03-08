package com.amoyiki.springtest.service.impl;

import com.amoyiki.springtest.entry.Role;
import com.amoyiki.springtest.repository.RoleRepository;
import com.amoyiki.springtest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author amoyiki
 * @since 2019/3/8
 */
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


//    @Override
//    public List<Role> findRoleListByUid(Long uid) {
//        return roleRepository.findRoleListByUid(uid);
//    }
}
