package com.yootk.mall.util;

import com.yootk.mall.vo.Member;

public class MallDataUtil { // 创建一个工具类
    public static final String LOGIN_COOKIE_KEY = "yootk-user"; // 做一个存储标记
    public static final Integer COOKIE_MAXAGE = 864000;
    private MallDataUtil() {}
    public static String createLoginData(Member member) {
        String content = member.getMid() + ":" + member.getName() + ":" + member.getLevel();
        return content;
    }
    public static Member parseLoginData(String content) {
        try {
            Member vo = new Member();
            String result[] = content.split(":"); // 根据“:”整合的数据
            vo.setMid(result[0]);
            vo.setName(result[1]);
            vo.setLevel(Integer.parseInt(result[2]));
            return vo;
        } catch (Exception e) {
            return null;
        }
    }
}
