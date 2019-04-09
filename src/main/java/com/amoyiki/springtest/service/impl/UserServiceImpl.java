package com.amoyiki.springtest.service.impl;

import com.amoyiki.springtest.annotation.DataSource;
import com.amoyiki.springtest.entry.Perm;
import com.amoyiki.springtest.entry.Role;
import com.amoyiki.springtest.entry.User;
import com.amoyiki.springtest.enums.DataSourceEnum;
import com.amoyiki.springtest.mapper.PermMapper;
import com.amoyiki.springtest.mapper.RoleMapper;
import com.amoyiki.springtest.mapper.UserMapper;
import com.amoyiki.springtest.service.UserService;
import com.amoyiki.springtest.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author amoyiki
 * @date 2019/3/7
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermMapper permMapper;

    // 切换数据源
    @Override
    @DataSource(DataSourceEnum.DB2)
    public UserInfo findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        if (user != null) {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(user, userInfo);

            List<Role> roleList = roleMapper.findRoleListByUid(user.getId());
            userInfo.setRoles(roleList.stream().map(role -> role.getCode()).collect(Collectors.toSet()));

            List<Integer> roleIdList =
                    (List<Integer>) roleList.stream().map(role -> role.getId()).collect(Collectors.toList());

            Set<String> permSet = new HashSet<>();
            for (Integer rid : roleIdList) {
                List<Perm> permList = permMapper.findPermListByRid(rid);
                permSet.addAll(permList.stream().map(perm -> perm.getCode()).collect(Collectors.toSet()));
            }
            userInfo.setPerms(permSet);
            log.info("├ [UserService] 按用户名查出User {}",userInfo);
            return userInfo;
        }
        return null;
    }
}
