package com.xmlmg.wechat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WechatUserInfo extends ErrorMessage {
    /**
     * 值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private int subscribe;

    private String openid;

    private String nickname;

    private long subscribe_time; // NOSONAR

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getSubscribe_time() { // NOSONAR
        return subscribe_time;
    }

    public void setSubscribe_time(long subscribe_time) { // NOSONAR
        this.subscribe_time = subscribe_time;
    }
}
