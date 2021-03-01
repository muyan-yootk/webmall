package com.yootk.mall.action.front;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.servlet.ModelAndView;
import com.yootk.common.servlet.ServletObject;
import com.yootk.common.util.encrypt.EncryptUtil;
import com.yootk.common.util.ftp.FTPUtil;
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
    @RequestMapping("/mid_check")
    public void checkMid(String mid) {  // 检测传入的用户id是否存在
        try {   // true表示可以使用、false表示不允许使用
            super.print(this.memberService.get(mid) == null); // 当前的用户ID可以使用
        } catch (Exception e) {
            super.print(false); // 用户ID无法使用
        }
    }

    /**
     * 用户登录处理
     * @param vo         包含有用户登录信息
     * @return 登录成功返回信息提示页（随后跳转到商品列表页），登录失败返回登录页
     */
    @RequestMapping("/member_login")
    public ModelAndView login(Member vo) throws Exception {
        String msg = super.getMessge("login.failure") ; // 保存返回的提示信息
        ModelAndView mav = new ModelAndView(super.getForwardPage()); // 最终跳转页
        vo.setPassword(EncryptUtil.getMD5Encode(vo.getPassword())); // 对密码进行加密处理
        if (this.memberService.login(vo)) { // 登录成功
            ServletObject.getSession().setAttribute("member", vo); // 实现用户登录状态的存储
            msg = super.getMessge("login.success"); // 登录成功的信息
        }
        mav.add(AbstractAction.PATH_ATTRIBUTE_NAME, super.getIndexPage()); // 登录成功返回到首页
        mav.add(AbstractAction.MSG_ATTRIBUTE_NAME, msg); // 登录的提示信息
        return mav;
    }

    @RequestMapping("/member_regist")
    public ModelAndView regist(Member vo) throws Exception {
        ModelAndView mav = new ModelAndView(super.getForwardPage()); // 最终跳转页
        vo.setPassword(EncryptUtil.getMD5Encode(vo.getPassword())); // 对密码进行加密处理

        try {
            String msg = super.getMessge("vo.add.failure", "用户");
            if (this.memberService.add(vo)) {
                msg = super.getMessge("vo.add.success", "用户"); // 保存成功
            }
            String path = super.getIndexPage(); // 跳转到首页
            mav.add(AbstractAction.PATH_ATTRIBUTE_NAME, path);
            mav.add(AbstractAction.MSG_ATTRIBUTE_NAME, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @Override
    public String getUploadPath() {
        return "/upload/member";
    }
}
