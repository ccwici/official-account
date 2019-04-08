package com.xmlmg.wechat.entity;

/**
 * 图文model
 */
public class Article {
    // 图文消息名称  
    private String title;
    // 图文消息描述  
    private String description;
    // 图片链接，支持JPG、PNG格式，较好的效果为大�?40*320，小�?0*80，限制图片链接的域名�?��与开发�?填写的基本资料中的Url�?��  
    private String picUrl;
    // 点击图文消息跳转链接  
    private String url;
  
    public String getTitle() {  
        return title;
    }  
  
    public void setTitle(String title) {  
        title = title;
    }  
  
    public String getDescription() {  
        return null == description ? "" : description;
    }  
  
    public void setDescription(String description) {  
        description = description;
    }  
  
    public String getPicUrl() {  
        return null == picUrl ? "" : picUrl;
    }  
  
    public void setPicUrl(String picUrl) {  
        picUrl = picUrl;
    }  
  
    public String getUrl() {  
        return null == url ? "" : url;
    }  
  
    public void setUrl(String url) {  
        url = url;
    } 
}