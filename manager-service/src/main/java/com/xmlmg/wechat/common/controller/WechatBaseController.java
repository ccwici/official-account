package com.xmlmg.wechat.common.controller;

import org.springframework.beans.factory.annotation.Value;

public class WechatBaseController {
    public static final String WX_SIGNATURE = "signature";
    public static final String WX_TIMESTAMP = "timestamp";
    public static final String WX_NONCE = "nonce";
    public static final String WX_ECHOSTR = "echostr";

    @Value("${public.account.token}")
    protected String publicAccountToken;

    @Value("${public.account.appId}")
    protected  String appId;

    @Value("${public.account.appSecret}")
    protected  String appSecret;

}
