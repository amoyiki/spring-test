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
public class Role implements Serializable {
    private static final long serialVersionUID = -2833418103499944462L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;       // 角色id
    private String name;   // 角色名，用于显示
    private String desc;   // 角色描述
    private String code;    // 角色值，用于权限判断
    private Date created;   // 创建时间
    private Date updated;   // 修改时间
    private String status;
}
