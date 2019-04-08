package com.xmlmg.wechat.entity;

import com.xmlmg.wechat.common.util.NetworkHelper;

import java.io.IOException;

public class AccessToken extends ErrorMessage {
    private String accessToken;
    private int expiresIn;
    private long expiresTime;

    private static AccessToken currentToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * 获取AccessToken对象
     */
    public static synchronized AccessToken getInstance(String appId, String appSecret) throws IOException {
        final long currentTime = System.currentTimeMillis();
        if(currentToken != null && currentTime < currentToken.expiresTime ) {
            return currentToken;
        }
        final String Url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",appId, appSecret);
        final AccessToken token = (AccessToken) NetworkHelper.get(Url,AccessToken.class);
        token.expiresTime = currentTime + token.getExpiresIn() * 1000 - 10000; // 减十秒，避免窗口期
        currentToken = token;
        return currentToken;
    }
}
