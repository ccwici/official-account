package com.xmlmg.wechat.service;

import com.xmlmg.wechat.entity.MessageResponse;
import com.xmlmg.wechat.util.MessageUtil;
import com.xmlmg.wechat.common.robot.TulingApiProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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
		String respMessage = null;
		// 默认返回的文本消息内容
		String respContent = "请求处理异常，请稍候尝试！";

		// xml请求解析
		// 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
		Map<String, String> requestMap = MessageUtil.parseXml(request);
		// 从HashMap中取出消息中的字段；
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		// 消息类型
		String msgType = requestMap.get("MsgType");
		// 消息内容
		String content = requestMap.get("Content");
		// 从HashMap中取出消息中的字段；

		logger.info("fromUserName is:" +fromUserName+" toUserName is:" +toUserName+" msgType is:" +msgType);

		// 文本消息
		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			//微信聊天机器人测试 2015-3-31
			respContent = getReplyMessageForTextMessage(content);
			return MessageResponse.getTextMessage(fromUserName , toUserName , respContent);
		} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 事件推送
			String eventType = requestMap.get("Event");// 事件类型
			return getReplyMessageForEvent(eventType, requestMap, fromUserName, toUserName);

		}
		//开启微信声音识别测试
		else if(msgType.equals("voice"))
		{
			respContent = getReplyMessageForVoiceMessage(requestMap);
			return MessageResponse.getTextMessage(fromUserName , toUserName , respContent);
		}
		//拍照功能
		else if(msgType.equals("pic_sysphoto"))
		{
			return MessageResponse.getTextMessage(fromUserName , toUserName , "漂亮");
		}
		else
		{
			return MessageResponse.getTextMessage(fromUserName , toUserName , "返回为空");
		}
	}

	private String getReplyMessageForVoiceMessage(Map<String, String> requestMap) {
		String respContent;
		String recvMessage = requestMap.get("Recognition");
		//respContent = "收到的语音解析结果："+recvMessage;
		if(recvMessage!=null){
			respContent = TulingApiProcess.getTulingResult(recvMessage);
		}else{
			respContent = "您说的太模糊了，能不能重新说下呢？";
		}
		return respContent;
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

	private String getReplyMessageForEvent(String eventType, Map<String, String> requestMap, String fromUserName, String toUserName) {
		if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {// 订阅
			return MessageResponse.getTextMessage(fromUserName , toUserName , subscriptionWelcome);
		} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
		} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
			String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
			logger.info("eventKey is:" +eventKey);
			return MenuClickService.getClickResponse(eventKey , fromUserName , toUserName);
		}
		return null;
	}

}