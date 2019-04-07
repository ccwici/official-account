package com.xmlmg.wechat.service.chat;

import com.xmlmg.wechat.common.robot.TulingApiProcess;
import com.xmlmg.wechat.entity.MessageResponse;

import java.util.Map;

public class TextMessageHandler extends AbstractMessageHandler {

    public TextMessageHandler(Map<String, String> requestMap) {
        super(requestMap);
    }

    @Override
    public String handle() {
        //微信聊天机器人测试 2015-3-31
        String respContent = getReplyMessageForTextMessage(content);
        return MessageResponse.getTextMessage(fromUserName , toUserName , respContent);
    }

    private String getReplyMessageForTextMessage(String requestMessage) {
        String respContent;
        if(requestMessage == null) {
            return "......";
        }
        respContent = TulingApiProcess.getTulingResult(requestMessage);
        if(respContent==""||null==respContent){
            respContent = "服务号暂时无法回复，请稍后再试！";
        }
        return respContent;
    }
}
