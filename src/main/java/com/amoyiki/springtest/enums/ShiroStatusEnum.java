package com.amoyiki.springtest.enums;

import lombok.Getter;

/**
 * @author amoyiki
 * @since 2019/3/6
 */
@Getter
public enum ShiroStatusEnum {
    TOKEN_ERROR("1000001", "token错误"),
    UNAUTHOR_ERROR("1000002", "用户无权限"),
    OTHER_ERROR("1000003", "其他错误"),
    ;
    private String code;
    private String msg;

    ShiroStatusEnum(String  code, String message){
        this.code = code;
        this.msg = message;
    }

}
