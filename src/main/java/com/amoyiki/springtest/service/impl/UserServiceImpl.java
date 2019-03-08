package com.amoyiki.springtest.service.impl;

import com.amoyiki.springtest.entry.User;
import com.amoyiki.springtest.repository.UserRepository;
import com.amoyiki.springtest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author amoyiki
 * @since 2019/3/7
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        Optional<User> o = userRepository.findByUsername(username);
        log.info("├ [UserService] 按用户名查出User {}",o.get().toString());
        return o.orElse(null);
    }
}
