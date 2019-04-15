package com.xmlmg.wechat.common.robot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用图灵机器人api接口，获取智能回复内�?
 *
 * @author pamchen-1
 */
public class TulingApiProcess {
    private static final Logger logger = LoggerFactory.getLogger(TulingApiProcess.class);

    /**
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果
     *
     * @param content
     * @return
     */
    public static String getTulingResult(String content) {
        //图灵机器人数据库接口
        final String APIKEY = "438cc658c632496fa4dc33511cb2674d";
        StringBuilder sb = new StringBuilder();
        String result = "";
        try {
            String info = URLEncoder.encode(content, "utf-8");
            String getURL = "http://www.tuling123.com/openapi/api?key="
                    + APIKEY + "&info=" + info;
            URL getUrl = new URL(getURL);
            HttpURLConnection connection = (HttpURLConnection) getUrl
                    .openConnection();
            connection.connect();

            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "utf-8"));

            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(sb.toString(), HashMap.class);
            result = (String) map.get("text");

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    private TulingApiProcess() {
        //
    }
}