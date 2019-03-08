package com.amoyiki.springtest.vo;

import lombok.Data;

/**
 * @description:
 * @author: amoyiki
 * @date: 2019/1/20
 */
@Data
public class ResultVO<T> {
     private String code;
     private String msg;
     private T data;
}
