package com.yootk.mall.action.back;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.servlet.ModelAndView;
import com.yootk.common.util.PageUtil;
import com.yootk.mall.service.back.IMemberServiceBack;

@Controller
@RequestMapping("/pages/back/admin/orders/")
public class OrdersActionBack extends AbstractAction {
	@Autowired
	private IMemberServiceBack memberServiceBack;
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(super.getPage("list.page"));
		return mav;
	}
	@RequestMapping("details")
	public ModelAndView detail() {
		ModelAndView mav = new ModelAndView(super.getPage("details.page"));
		return mav;
	}
	@Override
	public String getUploadPath() {
		return "upload/goods";
	}
}
