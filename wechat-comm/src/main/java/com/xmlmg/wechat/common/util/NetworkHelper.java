package com.xmlmg.wechat.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class NetworkHelper {

    public static Object get(String action, Class clz) throws IOException {
        String jsonStr = get(action);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return mapper.readValue(jsonStr, clz);
    }

    public static Object post(String action, String data, Class clz) throws IOException {
        String jsonStr = post(action, data);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return mapper.readValue(jsonStr, clz);
    }

    public static String get(String action) throws IOException {
        String result = null;
        URL url = new URL(action);

        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        http.setRequestMethod("GET");

        http.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000"); //NOSONAR
        System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // NOSONAR
        http.connect();
        InputStream is = http.getInputStream();
        result = readFromInputStream(is);
        log.debug("reply message:{}", result);
        return result;
    }

    public static String post(String action, String data) throws IOException {
        String result = null;
        URL url = new URL(action);

        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        http.setRequestMethod("POST");

        http.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        http.setDoOutput(true);
        http.setDoInput(true);
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
        System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes("UTF-8"));// 传入参数
            InputStream is = http.getInputStream();
            result = readFromInputStream(is);
            log.debug("reply message:{}", result);
        }
        return result;
    }

    private static String readFromInputStream(InputStream is) throws IOException {
        int size = is.available();
        byte[] jsonBytes = new byte[size];
        is.read(jsonBytes); // NOSONAR

        return new String(jsonBytes, "UTF-8");
    }

    private NetworkHelper() {
        //
    }
}
