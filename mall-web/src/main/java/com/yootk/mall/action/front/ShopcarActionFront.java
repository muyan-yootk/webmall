package com.yootk.mall.action.front;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.servlet.ModelAndView;

@Controller
@RequestMapping("/pages/front/center/shopcar/")
public class ShopcarActionFront extends AbstractAction {
    @RequestMapping("shopcar_list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView(super.getPage("list.page"));
        return mav;
    }

    @Override
    public String getUploadPath() {
        return "/upload/shopcar";
    }
}
