package com.amoyiki.springtest.controller;

import com.amoyiki.springtest.entry.User;
import com.amoyiki.springtest.form.LoginForm;
import com.amoyiki.springtest.utils.ResultVOUtil;
import com.amoyiki.springtest.vo.ResultVO;
import com.amoyiki.springtest.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author amoyiki
 * @since 2019/3/7
 */
@RestController
@Slf4j
public class LoginController {

    /**
     * 登录接口
     *
     * @param form
     * @return com.amoyiki.springtest.vo.ResultVO
     * @author amoyiki
     */
    @PostMapping(value = "/login")
    public ResultVO login(@Validated @RequestBody LoginForm form, BindingResult bindingResult,
                          HttpServletResponse response) throws BindException {
        log.info("body: {} error {}", form.toString(), bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(form.getUsername(), form.getPassword());
            subject.login(token);
            UserInfo user = (UserInfo) subject.getPrincipal();
            if (user == null) throw new AuthenticationException();
            Map<String, Object> resp = new HashMap<>();
            resp.put("token", subject.getSession().getId());
            return ResultVOUtil.success(resp);
        }

        /**
         * 未登录提示
         *
         * @param
         * @return com.amoyiki.springtest.vo.ResultVO
         * @author amoyiki
         */
        @GetMapping(value = "/unauth")
        public ResultVO unauth () {
            log.info("├ [未登录路由]: 无 Authorization 走当前路由");
            return ResultVOUtil.error("100000", "未登录");
        }
    }
