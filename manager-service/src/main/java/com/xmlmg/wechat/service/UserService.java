package com.xmlmg.wechat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmlmg.wechat.entity.AccessToken;
import com.xmlmg.wechat.entity.WechatUserInfo;
import com.xmlmg.wechat.util.NetworkHelper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserService {
    /**
     * {"total":1,"count":1,"data":{"openid":["osBu81drFg-lLNbDFkmbnDcsEvV8"]},"next_openid":"osBu81drFg-lLNbDFkmbnDcsEvV8"}
     */
    private String getUsersFromWeixin(String accessToken, String nextOpenid) throws IOException {
        String action = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken + "&next_openid=" + nextOpenid;
        String json = NetworkHelper.get(action);
        return json;
    }

    public List<WechatUserInfo> getUserInfosFromWeinxin(String accessToken, String nextOpenid) throws IOException {
        String json = getUsersFromWeixin(accessToken, nextOpenid);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, HashMap.class);
        Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
        List<WechatUserInfo> userList = new ArrayList<>();
        for( String openid : (List<String>)dataMap.get("openid")) {
            WechatUserInfo userInfo = getUserInfo(accessToken, openid);
            if(userInfo.getOpenid() == null) {
                continue; // 有问题的就不处理
            }
            userList.add(userInfo);
        }
        return userList;
    }

    public WechatUserInfo getUserInfo(String accessToken, String openid) throws IOException {
        String action = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openid;
        WechatUserInfo userInfo = (WechatUserInfo)NetworkHelper.get(action, WechatUserInfo.class);
        return userInfo;
    }
}
