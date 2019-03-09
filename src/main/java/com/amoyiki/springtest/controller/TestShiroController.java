package com.amoyiki.springtest.controller;

import com.amoyiki.springtest.utils.ResultVOUtil;
import com.amoyiki.springtest.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** 测试Shiro 权限
 * @author amoyiki
 * @since 2019/3/8
 */
@RestController
@Slf4j
public class TestShiroController {

    @GetMapping(value = "/authorize")
    @RequiresUser
    public ResultVO authorize () {
        return ResultVOUtil.error("0", "登录通过");
    }


    @GetMapping("/guest")
    @RequiresGuest
    public ResultVO guest() {
        return ResultVOUtil.error("0", "guest");
    }


    @GetMapping("/perm")
    @RequiresPermissions("TEST")
    public ResultVO perm() {
        return ResultVOUtil.error("0", "perm");
    }


    @GetMapping("/roles")
    @RequiresRoles("ADMIN")
    public ResultVO roles() {
        return ResultVOUtil.error("0", "roles");
    }

}
