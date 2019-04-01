package com.xmlmg.wechat.entity;

/**
 * http请求的request body
 */
public class PostBody {
    private String message;
    private String openid;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
