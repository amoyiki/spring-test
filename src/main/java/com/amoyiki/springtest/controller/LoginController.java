package com.amoyiki.springtest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amoyiki.springtest.entry.User;
import com.amoyiki.springtest.utils.ResultVOUtil;
import com.amoyiki.springtest.vo.ResultVO;
import com.amoyiki.springtest.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
     *
     * 登录接口
     * @author amoyiki
     * @param body
     * @return com.amoyiki.shirotest.vo.ResultVO
     */
    @PostMapping(value = "/login")
    public ResultVO login(@RequestBody String body){
        log.info("body: {}", body);

        JSONObject json = JSON.parseObject(body);
        String username = json.getString("username");
        String password = json.getString("password");
        // todo
        if (StringUtils.isEmpty(username)){
            return ResultVOUtil.erro("100003", "用户名不能为空");
        }
        if (StringUtils.isEmpty(password)){
            return ResultVOUtil.erro("100003", "密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        log.info("======{}=========={}",token.getUsername(),token.getPassword());
        subject.login(token);
        User user = (User) subject.getPrincipal();

        if (user==null) throw new AuthenticationException();
        Map<String, Object> resp = new HashMap<>();
        resp.put("token", subject.getSession().getId());
        return ResultVOUtil.success(resp);
//        try {
//
//        } catch (AuthenticationException e){
//            log.info("===================1111");
//            return ResultVOUtil.erro("100003", "账号密码错误");
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
    }

    /**
     *
     * 未登录提示
     * @author amoyiki
     * @param
     * @return com.amoyiki.shirotest.vo.ResultVO
     */
    @GetMapping(value = "/unauth")
    public ResultVO unauth(){
        return ResultVOUtil.erro("100000", "未登录");
    }

}
