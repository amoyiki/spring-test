package com.amoyiki.springtest.repository;


import com.amoyiki.springtest.entry.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @description:
 * @author: amoyiki
 * @date: 2019/2/28
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
