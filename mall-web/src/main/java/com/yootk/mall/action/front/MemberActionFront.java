package com.yootk.mall.action.front;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.servlet.ModelAndView;
import com.yootk.mall.service.front.IMemberServiceFront;
import com.yootk.mall.vo.Member;

@Controller
public class MemberActionFront extends AbstractAction {
    public static final String ACTION_TITLE = "用户";
    @Autowired
    private IMemberServiceFront memberService;

    /**
     * 登录前的页面跳转处理
     *
     * @return 返回到登录页
     */
    @RequestMapping("/member_login_pre")
    public ModelAndView loginPre() {
        ModelAndView mav = new ModelAndView(super.getPage("login.page"));
        return mav;
    }

    /**
     * 用户登录注销，登录注销后所有的Cookie信息将被删除
     *
     * @return 提示页面，随后跳转回登录页
     */
    @RequestMapping("/member_logout")
    public ModelAndView logout() {
        ModelAndView mav = new ModelAndView(super.getForwardPage());
        return mav;
    }

    /**
     * 验证码检测，用于ajax异步验证处理
     *
     * @param code 输入验证码
     */
    @RequestMapping("/code_check")
    public void check(String code) {
    }

    /**
     * 用户登录处理
     *
     * @param vo         包含有用户登录信息
     * @param rememberme 是否要执行免登录
     * @return 登录成功返回信息提示页（随后跳转到商品列表页），登录失败返回登录页
     */
    @RequestMapping("/member_login")
    public ModelAndView login(Member vo, String rememberme) throws Exception {
        return null;
    }

    @Override
    public String getUploadPath() {
        return "/upload/member";
    }
}
