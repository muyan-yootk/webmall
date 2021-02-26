package com.yootk.common.util;

public class StringUtil { // 实现字符串的相关处经理
    private StringUtil() {
    } // 构造方法私有化

    public static String initcap(String str) {
        if (str == null || "".equals(str)) {
            return str; // 现在内容是空字符串
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1); // 首字母大写
    }
    public static String firstLower(String str) {   // 首字母小写
        if (str == null || "".equals(str)) {
            return str;
        }
        if (str.length() == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1); //
    }
}
