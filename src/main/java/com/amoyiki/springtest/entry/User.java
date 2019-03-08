package com.amoyiki.springtest.entry;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: amoyiki
 * @date: 2019/2/28
 */
@Data
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -8664546652291057766L;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private Date createTime;
    private String status;



}
