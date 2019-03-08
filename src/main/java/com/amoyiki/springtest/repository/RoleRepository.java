package com.amoyiki.springtest.repository;

import com.amoyiki.springtest.entry.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author amoyiki
 * @since 2019/3/8
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     *
     * 根据用户 id 查找角色
     * @author amoyiki
     * @param uid
     * @return java.util.List<com.amoyiki.springtest.entry.Role>
     */
    @Query(value = "SELECT r FROM Role r LEFT JOIN UserRole ur ON r.id=ur.roleId WHERE ur.userId=:uid")
    List<Role> findRoleListByUid(Long uid);
}
