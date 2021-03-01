package com.yootk.mall.action.front;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.servlet.ModelAndView;
import com.yootk.common.servlet.ServletObject;
import com.yootk.mall.service.front.IMemberServiceFront;
import com.yootk.mall.vo.Member;

@Controller
public class CommonActionFront extends AbstractAction {
    @RequestMapping("/check_code")
    public void checkCode(String code) {
        String rand = (String) ServletObject.getSession().getAttribute("rand"); // 获取生成验证码
        System.out.println("********************* 用户输入的信息：" + code);
        System.out.println("********************* 生成的信息：" + rand);
        if (rand == null || "".equals(rand)) {
            super.print(false); // 验证码没有生成
        } else {
            super.print(rand.equalsIgnoreCase(code));
        }
    }
}
