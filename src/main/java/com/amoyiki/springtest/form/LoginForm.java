package com.amoyiki.springtest.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/** 登录数据验证
 * @author amoyiki
 * @since 2019/3/8
 */
@Data
public class LoginForm {
    @NotEmpty(message = "账号必填")
    private String username;
    @NotEmpty(message = "密码必填")
    private String password;

    public LoginForm(@NotEmpty(message = "账号必填") String username, @NotEmpty(message = "密码必填") String password) {
        this.username = username;
        this.password = password;
    }
}
