package com.yootk.mall.action.front;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.mvc.util.ResourceUtil;
import com.yootk.common.servlet.ModelAndView;
import com.yootk.common.servlet.ServletObject;
import com.yootk.common.util.PageUtil;
import com.yootk.mall.service.front.IOrdersServiceFront;
import com.yootk.mall.util.MallDataUtil;
import com.yootk.mall.vo.Member;
import com.yootk.mall.vo.Orders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/pages/front/center/orders/")
public class OrdersActionFront extends AbstractAction {
	public static final String ACTION_TITLE = "订单" ;
	@Autowired
	private IOrdersServiceFront ordersServiceFront; // 订单业务对象的注入
	/**
	 * 实现订单创建处理
	 * @return 订单创建页面
	 */
	@RequestMapping("add")
	public ModelAndView add(Orders orders, Long[] gid) {
		// 需要将gid的数组内容转为Set集合
		Set<Long> gids = new HashSet<>();
		gids.addAll(Arrays.asList(gid));
		Member member = (Member) ServletObject.getSession().getAttribute(MallDataUtil.LOGIN_SESSION_NAME);
		orders.setMid(member.getMid()); // 保存当前用户的ID编号
		ModelAndView mav = new ModelAndView(super.getForwardPage()) ;
		try {
			if (this.ordersServiceFront.add(orders,gids)) { // 成功
				mav.add(AbstractAction.PATH_ATTRIBUTE_NAME,super.getPage("list.action"));
				mav.add(AbstractAction.MSG_ATTRIBUTE_NAME, ResourceUtil.getMessage("vo.add.success",ACTION_TITLE));
			} else {	// 失败
				mav.add(AbstractAction.PATH_ATTRIBUTE_NAME,super.getIndexPage()); // 失败返回到首页路径上
				mav.add(AbstractAction.MSG_ATTRIBUTE_NAME, ResourceUtil.getMessage("vo.add.failure",ACTION_TITLE));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	/**
	 * 查看订单详情信息
	 * @return 订单详情显示
	 */
	@RequestMapping("orders_details_show")
	public ModelAndView show() {
		ModelAndView mav = new ModelAndView(super.getPage("show.page")) ;
		return mav ;
	}
	/**
	 * 实现订单创建前的处理
	 * @return 订单创建页面
	 */
	@RequestMapping("add_pre")
	public ModelAndView addPre(String ids) { // 增加前的处理操作
		Set<Long> gids = new HashSet<>();
		String result[] = ids.split(";");
		for (String gid : result) {
			gids.add(Long.parseLong(gid)); // 商品信息的增加
		}
		ModelAndView mav = new ModelAndView(super.getPage("add.page")) ;
		Member member = (Member) ServletObject.getSession().getAttribute(MallDataUtil.LOGIN_SESSION_NAME);
		try {
			mav.add(this.ordersServiceFront.preAdd(member.getMid(),gids));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	/**
	 * 实现订单信息列表显示
	 * @return 跳转到订单列表页
	 */
	@RequestMapping("orders_list")
	public ModelAndView list() {
		PageUtil pageUtil = new PageUtil(super.getPage("list.action"));
		Member member = (Member) ServletObject.getSession().getAttribute(MallDataUtil.LOGIN_SESSION_NAME);
		ModelAndView mav = new ModelAndView(super.getPage("list.page")) ;
		try {
			mav.add(this.ordersServiceFront.list(member.getMid(), pageUtil.getCurrentPage(), pageUtil.getLineSize()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}

	@Override
	public String getUploadPath() {
		return "/upload/orders";
	}
}
