package com.xmlmg.wechat.entity;

import java.util.List;

/** 
 * 图文消息 
 *  
 * @author jiangyin 
 */
public class NewsMessage extends BaseMessage {
    
    // 图文消息个数，限制为10条以�?
    private int articleCount;
    // 多条图文消息信息，默认第1条为大图
    private List<Article> articles;
  
    public int getArticleCount() {  
        return articleCount;
    }  
  
    public void setArticleCount(int articleCount) {  
        this.articleCount = articleCount;
    }  
  
    public List<Article> getArticles() {  
        return articles;
    }  
  
    public void setArticles(List<Article> articles) {  
        this.articles = articles;
    }
}