package com.xmlmg.wechat.common.util;

import com.xmlmg.wechat.entity.Article;
import com.xmlmg.wechat.entity.NewsMessage;
import com.xmlmg.wechat.entity.TextMessage;

import java.util.Date;
import java.util.List;

public class MessageResponse {

    private MessageResponse() {
        //
    }

    /**
     * 回复文本消息
     */
    public static String getTextMessage(String fromUserName , String toUserName , String respContent) {
        
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setFuncFlag(0);
        
        textMessage.setContent(respContent);
        return MessageUtil.textMessageToXml(textMessage);
    }
    
    /**
     * 创建图文消息
     */
    public static String getNewsMessage(String fromUserName , String toUserName , List<Article> articleList) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        
        // 设置图文消息个数
        newsMessage.setArticleCount(articleList.size());
        // 设置图文消息包含的图文集�?
        newsMessage.setArticles(articleList);
        // 将图文消息对象转换成xml字符�?
        return MessageUtil.newsMessageToXml(newsMessage);
    }
}