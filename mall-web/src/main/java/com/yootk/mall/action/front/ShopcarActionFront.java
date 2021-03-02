package com.yootk.mall.action.front;

import com.yootk.common.action.abs.AbstractAction;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Controller;
import com.yootk.common.mvc.annotation.RequestMapping;
import com.yootk.common.servlet.ModelAndView;
import com.yootk.common.servlet.ServletObject;
import com.yootk.mall.service.front.IShopcarServiceFront;
import com.yootk.mall.util.MallDataUtil;
import com.yootk.mall.vo.Member;
import com.yootk.mall.vo.Shopcar;

@Controller
@RequestMapping("/pages/front/center/shopcar/")
public class ShopcarActionFront extends AbstractAction {
    @Autowired
    private IShopcarServiceFront shopcarServiceFront; // 业务注入
    @RequestMapping("shopcar_list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView(super.getPage("list.page"));
        return mav;
    }

    /**
     * 前台可以传递过来的数据仅仅是一个商品的ID，而对于用户的ID必须通过Session获取
     * @param car
     */
    @RequestMapping("add")
    public void add(Shopcar car) {
        // 获取Session属性，因为所有登录成功的用户信息都会在session中保存有一个Member类的对象实例
        Member member = (Member) ServletObject.getSession().getAttribute(MallDataUtil.LOGIN_SESSION_NAME);
        System.out.println(member);
        car.setMid(member.getMid()); // 将当前的用户ID信息保存在购物车数据之中
        try {
            super.print(this.shopcarServiceFront.add(car));
        } catch (Exception e) {
            e.printStackTrace();
            super.print(false);
        }
    }

    @Override
    public String getUploadPath() {
        return "/upload/shopcar";
    }
}
