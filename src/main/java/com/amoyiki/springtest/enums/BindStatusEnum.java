package com.amoyiki.springtest.enums;

import lombok.Getter;

/** 参数绑定错误类型
 * @author amoyiki
 * @since 2019/3/8
 */
@Getter
public enum BindStatusEnum {
    PARAM_ERROR("100201", "参数错误"),
    NOT_VALUE_ERROR("100202", "缺少必填参数"),
    NOT_NULL_ERROR("100203", "参数必须不为空"),
    ;
    private String code;
    private String msg;

    BindStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
