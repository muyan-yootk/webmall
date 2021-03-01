package com.yootk.mall.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/pages/front/center/*")
public class FrontUserLoginFilter extends HttpFilter { // 前端的Filter检测

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(); // 获取当前的session
        if (session.getAttribute("member") != null) { // 用户登录过了
            chain.doFilter(req, res); // 正常向下进行访问
        } else {    // 没有进行过用户登录处理，所以为空
            req.setAttribute("msg", "您还未登录，请先进行登录操作！");
            req.setAttribute("url", "/member_login_pre.action");
            req.getRequestDispatcher("/pages/plugins/forward.jsp").forward(req, res);
        }
    }
}
