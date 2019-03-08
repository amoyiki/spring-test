package com.amoyiki.springtest.entry;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author amoyiki
 * @since 2019/3/7
 */
@Entity
@Data
public class Perm implements Serializable {
    private static final long serialVersionUID = 2365700163036408260L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;       // 权限id
    private String name;   // 权限名称
    private Integer type;  // 权限类型：1.菜单；2.按钮
    private String code;    // 权限值，shiro的权限控制表达式
    private Date created;   // 创建时间
    private Date updated;   // 修改时间
    private String status;

}
