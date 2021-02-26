package com.yootk.mall.filter;


import com.yootk.common.servlet.CookieUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class MemberCookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute("mid") == null) {    // 用户未登录
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String info = CookieUtil.get("info", request);
            if (info != null) {
                String temp[] = info.split(":");
                session.setAttribute("mid", temp[0]);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } else {    // 用户已登录
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
