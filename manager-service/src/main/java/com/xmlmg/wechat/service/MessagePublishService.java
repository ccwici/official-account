package com.xmlmg.wechat.service;

import com.xmlmg.wechat.common.util.StringUtils;
import com.xmlmg.wechat.entity.ErrorMessage;
import com.xmlmg.wechat.common.util.NetworkHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 主动调用微信接口服务
 */
@Component
public class MessagePublishService {
    private static Logger logger = LoggerFactory.getLogger(MessagePublishService.class);

    /**
     * 微信公共账号发送给账号
     *
     * @param content 文本内容
     * @param toUser  微信用户
     * @return 是否成功
     */
    public ErrorMessage sendTextMessageToUser(String content, String toUser, String accessToken) throws IOException {
        if(StringUtils.isEmpty(content)) {
            return new ErrorMessage("400", "内容不能为空");
        }

        String json = "{\"touser\": \"" + toUser + "\",\"msgtype\": \"text\", \"text\": {\"content\": \"" + content + "\"}}";

        //获取请求路径
        String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        logger.debug("request message:{}" + json);
        return (ErrorMessage) NetworkHelper.post(action, json, ErrorMessage.class);
    }

}
