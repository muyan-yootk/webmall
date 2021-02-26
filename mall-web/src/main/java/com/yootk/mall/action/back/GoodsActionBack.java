package com.yootk.mall.action.back;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.servlet.ModelAndView;
import com.yootk.common.servlet.MultipartFile;
import com.yootk.common.util.PageUtil;
import com.yootk.mall.service.back.IGoodsServiceBack;
import com.yootk.mall.util.UploadFileToServer;
import com.yootk.mall.vo.Goods;

@Controller
@RequestMapping("/pages/back/admin/goods/")
public class GoodsActionBack extends AbstractAction {
	@Autowired
	private IGoodsServiceBack goodsService;
	@RequestMapping("add_pre")
	public String addPre() {
		return super.getPage("add.page") ;
	}
	@RequestMapping("add")
	public ModelAndView add(Goods goods, MultipartFile file) {
		ModelAndView mav = new ModelAndView(super.getForwardPage()) ;
		try {
			String fileName = UploadFileToServer.upload(file,file.getContentType()) ;
			goods.setPhoto(fileName);
			String msg = super.getMessge("vo.add.failure","商品") ;
			if (this.goodsService.add(goods)) {
				msg = super.getMessge("vo.add.success","商品") ;
			}
			String path = super.getPage("add.action") ;
			mav.add(AbstractAction.PATH_ATTRIBUTE_NAME, path);
			mav.add(AbstractAction.MSG_ATTRIBUTE_NAME, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	@RequestMapping("list")
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
	@Override
	public String getUploadPath() {
		return "upload/goods";
	}
}
