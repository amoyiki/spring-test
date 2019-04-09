package com.amoyiki.springtest.exception;

import com.amoyiki.springtest.enums.ShiroStatusEnum;
import lombok.Data;

/** 自定义异常继承 RuntimeException 方便 Spring 事务回滚
 * @author amoyiki
 * @date 2019/3/8
 */
@Data
public class MyException extends RuntimeException {
    private String code;

    public MyException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public MyException(ShiroStatusEnum statusEnum) {
        super(statusEnum.getMsg());
        this.code = statusEnum.getCode();
    }
}
