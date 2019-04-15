package com.xmlmg.wechat.common.util;

public class StringUtils {
    public static boolean isNotEmpty(String string) {
        return string != null && !"".equals(string);
    }

    public static boolean isEmpty(String string) {
        return string == null || "".equals(string);
    }

    private StringUtils() {
        //
    }
}
