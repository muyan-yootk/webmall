package com.yootk.mall.filter;
import com.yootk.common.servlet.CookieUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class MemberFrontLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req ;
        HttpSession session = request.getSession() ;
        if (session.getAttribute("mid") == null) {	// 用户未登录
            HttpServletResponse response = (HttpServletResponse) resp ;
            String info = CookieUtil.get("info",request) ;
            if (info != null) {
                String temp [] = info.split(":") ;
                if (temp[0] != null) {
                    session.setAttribute("mid", temp[0]);
                    chain.doFilter(req, resp);
                } else {	// 跳转到登录页
                    req.getRequestDispatcher("/member_login_pre.action").forward(req, resp);
                }
            } else {
                req.getRequestDispatcher("/member_login_pre.action").forward(req, resp);
            }
        } else {	// 用户已登录
            chain.doFilter(req, resp);
        }
    }
}
