package com.yootk.mall.action.back;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.servlet.ModelAndView;
import com.yootk.common.util.PageUtil;
import com.yootk.mall.service.back.IMemberServiceBack;
import com.yootk.mall.service.back.IOrdersServiceBack;

@Controller
@RequestMapping("/pages/back/admin/orders/")
public class OrdersActionBack extends AbstractAction {
	@Autowired
	private IOrdersServiceBack ordersServiceBack;
	@RequestMapping("list")
	public ModelAndView list() {
		PageUtil pageUtil = new PageUtil(super.getPage("list.action"));
		ModelAndView mav = new ModelAndView(super.getPage("list.page"));
		try {
			mav.add(this.ordersServiceBack.list(pageUtil.getColumn(), pageUtil.getKeyword(), pageUtil.getCurrentPage(), pageUtil.getLineSize()));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
