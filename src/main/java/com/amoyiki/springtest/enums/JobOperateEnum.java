package com.amoyiki.springtest.enums;

import lombok.Getter;

/**
 * @author amoyiki
 * @since 2019/3/27
 */
@Getter
public enum JobOperateEnum {
    START(1,"启动"),
    PAUSE(2, "暂停"),
    DELETE(3, "删除")
    ;
    private Integer code;
    private String msg;

    JobOperateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
