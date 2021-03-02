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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pages/front/center/shopcar/")
public class ShopcarActionFront extends AbstractAction {
    @Autowired
    private IShopcarServiceFront shopcarServiceFront; // 业务注入

    @RequestMapping("remove_batch")
    public void removeBatch(String data) { // 要修改的数据项，数据格式：gid|gid|gid
        Member member = (Member) ServletObject.getSession().getAttribute(MallDataUtil.LOGIN_SESSION_NAME);
        String result[] = data.split("\\|");
        List<Shopcar> carList = new ArrayList<>();
        for (String res : result) {
            Shopcar car = new Shopcar();
            car.setMid(member.getMid());
            car.setGid(Long.parseLong(res)); // 商品编号
            carList.add(car); // 保存在List集合
        }
        try {
            super.print(this.shopcarServiceFront.deleteBatch(carList));
        } catch (Exception e) {
            e.printStackTrace();
            super.print(false);
        }
    }


    @RequestMapping("edit_batch")
    public void editBatch(String data) { // 要修改的数据项
        // 假设现在传递的修改参数的内容为：gid:amount|gid:amount|...
        Member member = (Member) ServletObject.getSession().getAttribute(MallDataUtil.LOGIN_SESSION_NAME);
        String result[] = data.split("\\|");
        List<Shopcar> carList = new ArrayList<>();
        for (String res : result) {
            String temp [] = res.split(":");
            Shopcar car = new Shopcar();
            car.setMid(member.getMid());
            car.setGid(Long.parseLong(temp[0])); // 商品编号
            car.setAmount(Integer.parseInt(temp[1])); // 商品数量
            carList.add(car); // 保存在List集合
        }
        try {
            super.print(this.shopcarServiceFront.editBatchAmount(carList));
        } catch (Exception e) {
            e.printStackTrace();
            super.print(false);
        }
    }
    @RequestMapping("edit")
    public void edit(Shopcar car) {
        Member member = (Member) ServletObject.getSession().getAttribute(MallDataUtil.LOGIN_SESSION_NAME);
        car.setMid(member.getMid()); // 将当前的用户ID信息保存在购物车数据之中
        try {
            super.print(this.shopcarServiceFront.editAmount(car));
        } catch (Exception e) {
            e.printStackTrace();
            super.print(false);
        }
    }

    @RequestMapping("shopcar_list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView(super.getPage("list.page"));
        Member member = (Member) ServletObject.getSession().getAttribute(MallDataUtil.LOGIN_SESSION_NAME);
        try {
            mav.add(this.shopcarServiceFront.list(member.getMid())); // 实现数据查询
        } catch (Exception e) {
            e.printStackTrace();
        }
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
