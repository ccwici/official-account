package com.xmlmg.wechat.controller.auth;

import com.xmlmg.wechat.common.util.StringUtils;
import com.xmlmg.wechat.entity.auth.SysUser;
import com.xmlmg.wechat.service.ISysUserService;
import com.xmlmg.wechat.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @version: 1.0
 */
@Api(tags = "登录鉴权")
@RestController
public class AuthController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping(value = "${jwt.route.login}")
    @ApiOperation("登录")
    public Result<String> login(@RequestBody SysUser sysUser) {
        String username = sysUser.getUsername();
        String password = sysUser.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.error401("用户或者密码不能为空！", null);
        }
        return Result.success("登录成功", sysUserService.login(username, password));
    }

    @ApiOperation("刷新token")
    @PostMapping(value = "${jwt.route.refresh}")
    public Result<String> refresh(@RequestHeader("${jwt.header}") String token) {
        return Result.success("刷新token成功!", sysUserService.refreshToken(token));
    }

}
