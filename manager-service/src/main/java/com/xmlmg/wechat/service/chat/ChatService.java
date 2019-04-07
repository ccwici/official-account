package com.xmlmg.wechat.service.chat;

import com.xmlmg.wechat.entity.MessageResponse;
import com.xmlmg.wechat.service.MenuClickService;
import com.xmlmg.wechat.util.MessageUtil;
import com.xmlmg.wechat.common.robot.TulingApiProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChatService {
	private final Logger logger = LoggerFactory.getLogger(ChatService.class);

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
		Map<String, String> requestMap = MessageUtil.parseXml(request);
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