package com.amoyiki.springtest.entry;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author amoyiki
 * @since 2019/3/7
 */
@Entity
@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = 2453814492325477077L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long roleId;

}
