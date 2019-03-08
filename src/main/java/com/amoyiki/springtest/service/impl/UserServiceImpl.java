package com.amoyiki.springtest.service.impl;

import com.amoyiki.springtest.entry.Perm;
import com.amoyiki.springtest.entry.Role;
import com.amoyiki.springtest.entry.User;
import com.amoyiki.springtest.repository.PermRepository;
import com.amoyiki.springtest.repository.RoleRepository;
import com.amoyiki.springtest.repository.UserRepository;
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
 * @since 2019/3/7
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermRepository permRepository;

    @Override
    public UserInfo findByUsername(String username) {
        Optional<User> o = userRepository.findByUsername(username);
        if (o.isPresent()) {
            User user = (User)o.get();
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(user, userInfo);

            List<Role> roleList = roleRepository.findRoleListByUid(user.getId());
            userInfo.setRoles(roleList.stream().map(role -> role.getCode()).collect(Collectors.toSet()));

            List<Long> roleIdList = (List<Long>) roleList.stream().map(role -> role.getId()).collect(Collectors.toList());

            Set<String> permSet = new HashSet<>();
            for (Long rid : roleIdList) {
                List<Perm> permList = permRepository.findPermListByRid(rid);
                permSet.addAll(permList.stream().map(perm -> perm.getCode()).collect(Collectors.toSet()));
            }
            userInfo.setPerms(permSet);
            return userInfo;
        }
        log.info("├ [UserService] 按用户名查出User {}",o.get().toString());
        return null;
    }
}
