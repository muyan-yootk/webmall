package com.yootk.mall.action.front;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.servlet.ModelAndView;
import com.yootk.common.util.PageUtil;
import com.yootk.mall.service.front.IGoodsServiceFront;

@Controller
@RequestMapping("/pages/front/goods/")
public class GoodsActionFront extends AbstractAction {
	@Autowired
	private IGoodsServiceFront goodsService;
	@RequestMapping("goods_list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(super.getPage("list.page"));
		PageUtil pu = new PageUtil(super.getPage("list.action"),"商品名称:name");
		try {
			mav.add(
					this.goodsService.list(pu.getCurrentPage(), pu.getLineSize(),pu.getColumn(), pu.getKeyword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	public String getUploadDir() {
		return "upload/goods";
	}
}
