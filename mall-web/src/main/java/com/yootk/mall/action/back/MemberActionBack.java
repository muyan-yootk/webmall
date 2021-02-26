package com.yootk.mall.action.back;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.servlet.ModelAndView;
import com.yootk.common.servlet.MultipartFile;
import com.yootk.common.util.PageUtil;
import com.yootk.mall.service.back.IGoodsServiceBack;
import com.yootk.mall.service.back.IMemberServiceBack;
import com.yootk.mall.util.UploadFileToServer;
import com.yootk.mall.vo.Goods;

@Controller
@RequestMapping("/pages/back/admin/member/")
public class MemberActionBack extends AbstractAction {
	@Autowired
	private IMemberServiceBack memberServiceBack;
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(super.getPage("list.page"));
		PageUtil pu = new PageUtil(super.getPage("list.action"),"商品名称:name");
		try {
			mav.add(
					this.memberServiceBack.list(pu.getCurrentPage(), pu.getLineSize(),pu.getColumn(), pu.getKeyword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	@Override
	public String getUploadPath() {
		return "upload/goods";
	}
}
