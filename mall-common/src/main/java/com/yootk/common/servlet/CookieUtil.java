package com.yootk.common.servlet;


import com.yootk.common.util.encrypt.EncryptSalt;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.concurrent.TimeUnit;

public class CookieUtil {
    private CookieUtil() {}
    public static void set(String name, String value, HttpServletResponse resp) {// value = mid:password
        String val = EncryptSalt.encrypt(value) ; // 将数据内容进行加密存储
        Cookie c = new Cookie(name,val) ; // 创建Cookie的对象
        c.setPath("/"); // 设置目录
        c.setMaxAge((int)TimeUnit.SECONDS.convert(30,TimeUnit.DAYS));
        resp.addCookie(c); // 保存Cookie数据
    }
    public static String get(String name, HttpServletRequest req) {
        Cookie cookie [] = req.getCookies() ; // 获取全部的Cookie内容
        if (cookie != null) {
            for (int x = 0 ; x < cookie.length ; x ++) {
                if (name.equalsIgnoreCase(cookie[x].getName())) {
                    if (!"".equals(cookie[x].getValue())) {
                        return EncryptSalt.decrypt(cookie[x].getValue());
                    }
                }
            }
        }
        return null ;
    }
    public static void clean(HttpServletResponse resp,String ... key) {
        for (String name : key) {
            Cookie c = new Cookie(name,"") ; // 创建Cookie的对象
            c.setPath("/"); // 设置目录
            resp.addCookie(c);
        }
    }
}

