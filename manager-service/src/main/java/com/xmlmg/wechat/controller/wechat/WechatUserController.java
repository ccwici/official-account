package com.xmlmg.wechat.controller.wechat;

import com.xmlmg.wechat.entity.AccessToken;
import com.xmlmg.wechat.entity.WechatUserInfo;
import com.xmlmg.wechat.service.UserService;
import com.xmlmg.wechat.vo.Result;
import com.xmlmg.wechat.common.controller.WechatBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 管理员接口
 */
@RestController
public class WechatUserController extends WechatBaseController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/list/")
    public Result getUsersFromWeixin() throws IOException {
        List<WechatUserInfo> users =  userService.getUserInfosFromWeinxin(AccessToken.getInstance(appId, appSecret).getAccessToken(), "");
        return Result.success(users);
    }

}
