package com.amoyiki.springtest.repository;

import com.amoyiki.springtest.entry.Perm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author amoyiki
 * @since 2019/3/8
 */
public interface PermRepository extends JpaRepository<Perm, Long> {

    /**
     * 按角色id 列表查找权限列表
     *
     * @author amoyiki
     * @param rid
     * @return java.util.List<com.amoyiki.springtest.entry.Perm>
     */
    @Query(value = "select p " +
            "FROM Perm p LEFT JOIN RolePerm rp ON p.id=rp.permId WHERE rp.roleId =:rid")
    List<Perm> findPermListByRid(@Param("rid") Long rid);


}
