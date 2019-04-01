package com.xmlmg.wechat.controller.wechat;

import com.xmlmg.wechat.common.controller.WechatBaseController;
import com.xmlmg.wechat.entity.AccessToken;
import com.xmlmg.wechat.entity.ErrorMessage;
import com.xmlmg.wechat.service.MessagePublishService;
import com.xmlmg.wechat.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/message/publish/")
public class MessagePublishController extends WechatBaseController {
    @Autowired
    MessagePublishService messagePublishService;

    //TODO 发送个人消息   整体推送消息
    @PostMapping(value="/personal/")
    public Result sendPersonalMessage(@RequestParam String openid, @RequestParam String message) throws IOException {
        String accessToken = AccessToken.getInstance(appId, appSecret).getAccessToken();
        if(accessToken == null) {
            return Result.error500(null, "token创建失败");
        }
        ErrorMessage errorMessage = messagePublishService.sendTextMessageToUser(message, openid, accessToken);
        if("0".equals(errorMessage.getErrcode())) {
            return Result.success("success");
        } else {
            return Result.error500(errorMessage.getErrmsg(), errorMessage.getErrmsg());
        }
    }
}
