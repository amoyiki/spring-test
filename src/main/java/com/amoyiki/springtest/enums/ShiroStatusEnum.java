package com.amoyiki.springtest.enums;

import lombok.Getter;

/**
 * @author amoyiki
 * @date 2019/3/6
 */
@Getter
public enum ShiroStatusEnum {
    TOKEN_ERROR("100100", "token错误"),
    UNAUTHOR_ERROR("100102", "用户无权限"),
    OTHER_ERROR("100103", "其他错误"),
    ;
    private String code;
    private String msg;

    ShiroStatusEnum(String  code, String message){
        this.code = code;
        this.msg = message;
    }

}
