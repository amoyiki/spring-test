package com.amoyiki.springtest.entry;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author amoyiki
 * @since 2019/3/7
 */
@Entity
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 2894041875355222436L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;       // 用户id
    private String username;   // 登录名，不可改
    private String password;     // 已加密的登录密码
    private String salt;    // 加密盐值
    private Date created;   // 创建时间
    private Date updated;   // 修改时间
    private String status;
}