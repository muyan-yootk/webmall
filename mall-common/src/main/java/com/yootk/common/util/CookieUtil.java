package com.yootk.common.util;

import com.yootk.common.servlet.ServletObject;
import com.yootk.common.util.encrypt.EncryptUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtil { // 创建一个Cookie的工具类
    private CookieUtil() {}
    /**
     * 实现Cookie数据的存储
     * @param key 要存储的key信息
     * @param value 要存储的数据内容
     * @param maxAge Cookie保存的时长（单位：秒）
     */
    public static void save(String key, String value, int maxAge) {
        // 在WEB开发之中所有Cookie的数据本质上都属于明文信息，既然是明文内容就需要进行加密处理
        // 既然要进行免登录的控制，那么就一定会存在有不登录直接打开路径的情况，可以尝试在过滤器之中做一个登录处理
        String encodeContent = EncryptUtil.encrypt(value); // 数据进行可逆加密
        Cookie cookie = new Cookie(key, encodeContent);// 创建Cookie对象
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        ServletObject.getResponse().addCookie(cookie);// 保存Cookie到客户端
    }

    /**
     * 根据指定的名称读取Cookie的内容
     * @param request 过滤器所传递request内置对象
     * @param key 要读取的Cookie的名称
     * @return 指定名称的内容，如果不存在则返回null
     */
    public static String load(HttpServletRequest request, String key) {
        Cookie cookies [] = request.getCookies(); // 获取全部Cookie信息
        if (cookies != null) {  // 现在存在有Cookie的数据信息
            for (Cookie c : cookies) {
                if (c.getName().equals(key)) {  // 与要查询的key相匹配
                    return EncryptUtil.decrypt(c.getValue());
                }
            }
        }
        return null;
    }

    /**
     * Cookie数据清除
     * @param key 要清除的key的内容
     */
    public static void clean(String key) {
        Cookie cookie = new Cookie(key, "www.yootk.com");// 创建Cookie对象
        cookie.setMaxAge(0); // 让Cookie的时间为0
        ServletObject.getResponse().addCookie(cookie);// 保存Cookie到客户端
    }
}
