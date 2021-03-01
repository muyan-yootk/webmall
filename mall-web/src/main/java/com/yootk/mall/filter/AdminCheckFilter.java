package com.yootk.mall.filter;

import com.yootk.mall.util.MallDataUtil;
import com.yootk.mall.vo.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/pages/back/*") // 设置过滤监测路径
public class AdminCheckFilter extends HttpFilter { // 过滤器

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Member member = (Member) req.getSession().getAttribute(MallDataUtil.LOGIN_SESSION_NAME);
        if (member != null) {   // 存在有登录数据
            if (member.getLevel().equals(1)) {  // 管理员标记level内容为1
                chain.doFilter(req, res); // 允许向后继续访问
            } else {    // 不是1就是0
                req.setAttribute("msg", "您不是管理员，别干越权的事情，否则容易被劈！");
                req.setAttribute("url", "/member_login_pre.action");
                req.getSession().invalidate(); // Session注销
                req.getRequestDispatcher("/pages/plugins/forward.jsp").forward(req, res);
            }
        } else { // 没有登陆数据
            req.setAttribute("msg", "您还未登录，请先进行登录操作！");
            req.setAttribute("url", "/member_login_pre.action");
            req.getRequestDispatcher("/pages/plugins/forward.jsp").forward(req, res);
        }
        chain.doFilter(req, res);
    }
}
