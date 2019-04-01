package com.xmlmg.wechat.controller.auth;

import com.xmlmg.wechat.common.controller.BaseController;
import com.xmlmg.wechat.entity.auth.SysUser;
import com.xmlmg.wechat.service.ISysUserService;
import com.xmlmg.wechat.vo.Result;
import com.xmlmg.wechat.vo.SysUserVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController<SysUser, String, ISysUserService> {

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        if("admin".equals(username) && "admin".equals(password)) {
            return Result.success("登陆成功", "tokentokentoken");
        }
        return Result.error401("非法用户密码！", null);
    }

    @GetMapping("/findByUsername")
    public SysUser findByUsername(@RequestParam String username) {
        return baseService.findByUsername(username);
    }

    @GetMapping("/info")
    public Result<SysUserVo> info() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUserVo userInfo = baseService.findUserInfo(userDetails.getUsername());
        return Result.success(userInfo);
    }
}
