package com.xmlmg.wechat.handler;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public abstract class AbstractMessageHandler {
    protected String fromUserName;
    protected String toUserName;
    protected String msgType;
    protected String content;

    protected Map<String, String> requestMap;

    public AbstractMessageHandler(Map<String, String> requestMap){
        this.requestMap = requestMap;
        // 从HashMap中取出消息中的字段；
        // 发送方帐号（open_id）
        this.fromUserName = requestMap.get("FromUserName");
        // 公众帐号
        this.toUserName = requestMap.get("ToUserName");
        // 消息类型
        this.msgType = requestMap.get("MsgType");
        // 消息内容
        this.content = requestMap.get("Content");
        // 从HashMap中取出消息中的字段；
        log.debug("fromUserName is:" +fromUserName+" toUserName is:" +toUserName+" msgType is:" +msgType);
    }

    public abstract String handle();

}
