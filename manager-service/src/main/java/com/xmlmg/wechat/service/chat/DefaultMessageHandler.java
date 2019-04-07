package com.xmlmg.wechat.service.chat;

import com.xmlmg.wechat.entity.MessageResponse;

import java.util.Map;

public class DefaultMessageHandler extends AbstractMessageHandler {

    public DefaultMessageHandler(Map<String, String> requestMap) {
        super(requestMap);
    }

    @Override
    public String handle() {
        return MessageResponse.getTextMessage(fromUserName , toUserName , "小白不懂，请您跟小白换种方式交流！");
    }
}
