package com.amoyiki.springtest.entry;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * OAUTH2 客户端表
 * @author amoyiki
 * @since 2019/3/11
 */
@Entity
@Data
public class Client implements Serializable {

    private static final long serialVersionUID = 6993022190365499257L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private String clientId;
    private String clientSecret;
}
