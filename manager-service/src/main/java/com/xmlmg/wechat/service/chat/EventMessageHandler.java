package com.xmlmg.wechat.service.chat;

import com.xmlmg.wechat.entity.MessageResponse;
import com.xmlmg.wechat.service.MenuClickService;
import com.xmlmg.wechat.common.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class EventMessageHandler extends AbstractMessageHandler {

    private String subscriptionWelcome;

    public EventMessageHandler(Map<String, String> requestMap, String subscriptionWelcome) {
        super(requestMap);
        this.subscriptionWelcome = subscriptionWelcome;
    }

    public EventMessageHandler(Map<String, String> requestMap) {
        super(requestMap);
    }

    @Override
    public String handle() {
        String eventType = requestMap.get("Event");// 事件类型
        return getReplyMessageForEvent(eventType, requestMap, fromUserName, toUserName);
    }

    private String getReplyMessageForEvent(String eventType, Map<String, String> requestMap, String fromUserName, String toUserName) {
        if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {// 订阅
            return MessageResponse.getTextMessage(fromUserName , toUserName , subscriptionWelcome);
        } else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
        } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
            String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
            log.info("eventKey is:" +eventKey);
            return MenuClickService.getClickResponse(eventKey , fromUserName , toUserName);
        }
        return null;
    }
}
