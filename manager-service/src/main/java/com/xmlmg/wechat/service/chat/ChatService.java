package com.xmlmg.wechat.service.chat;

import com.xmlmg.wechat.common.util.MessageUtil;
import com.xmlmg.wechat.handler.AbstractMessageHandler;
import com.xmlmg.wechat.handler.DefaultMessageHandler;
import com.xmlmg.wechat.handler.EventMessageHandler;
import com.xmlmg.wechat.handler.TextMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ChatService {

	@Value("${public.account.subscription.welcome}")
	private String subscriptionWelcome;
	
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) throws Exception {
		// xml请求解析
		// 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
		Map<String, String> requestMap = MessageUtil.parseXml(request.getInputStream());
		Map<String, AbstractMessageHandler> handlerMap = new HashMap<>();
		// 消息类型
		final String msgType = requestMap.get("MsgType");

		// 文本消息
		handlerMap.put(MessageUtil.REQ_MESSAGE_TYPE_TEXT, new TextMessageHandler(requestMap));

		// 事件推送
		handlerMap.put(MessageUtil.REQ_MESSAGE_TYPE_EVENT, new EventMessageHandler(requestMap, subscriptionWelcome));

		DefaultMessageHandler defaultMessageHandler = new DefaultMessageHandler(requestMap);
		// 语音
		handlerMap.put(MessageUtil.REQ_MESSAGE_TYPE_VOICE, defaultMessageHandler);

		// 其他
		AbstractMessageHandler messageHandler = handlerMap.get(msgType);

		if(messageHandler == null) {
			messageHandler = defaultMessageHandler;
		}
		return messageHandler.handle();
	}
}