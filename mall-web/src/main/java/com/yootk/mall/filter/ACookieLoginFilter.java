package com.yootk.mall.filter;

import com.yootk.common.util.CookieUtil;
import com.yootk.mall.util.MallDataUtil;
import com.yootk.mall.vo.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class ACookieLoginFilter extends HttpFilter { // 实现Cookie登录的过滤器

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String value = CookieUtil.load(req, MallDataUtil.LOGIN_COOKIE_KEY); // 获取当前的Cookie数据
        if (value != null) {    // 此时存在有Cookie信息
            Member member = MallDataUtil.parseLoginData(value); // 将Cookie数据进行解析
            if (member != null) {   // 当前存在有member正确数据
                req.getSession().setAttribute("member", member); // 保存在session之中
            }
        }
        chain.doFilter(req, res); // 直接放行
    }
}
