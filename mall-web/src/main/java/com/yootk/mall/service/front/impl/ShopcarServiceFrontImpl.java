package com.yootk.mall.service.front.impl;

import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.service.abs.AbstractService;
import com.yootk.mall.dao.IShopcarDAO;
import com.yootk.mall.service.front.IShopcarServiceFront;
import com.yootk.mall.vo.Shopcar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Aspect
public class ShopcarServiceFrontImpl extends AbstractService implements IShopcarServiceFront {
    @Autowired
    private IShopcarDAO shopcarDAO; // 注入DAO对象实例

    @Override
    public boolean editBatchAmount(List<Shopcar> cars) throws Exception {
        if (cars == null || cars.size() == 0) { // 集合不存在
            return false;
        }
        return this.shopcarDAO.doEditAmountBatch(cars);
    }

    @Override
    public boolean editAmount(Shopcar car) throws Exception {
        if (car.getAmount() == 0) { // 表示删除
            return this.shopcarDAO.doRemoveByMidAndGid(car.getMid(), car.getGid());
        } else {
            return this.shopcarDAO.doEditAmount(car.getMid(), car.getGid(), car.getAmount());
        }
    }

    @Override
    public boolean add(Shopcar shopcar) throws Exception {
        // 1、在进行增加之前首先一定要判断该条数据内容是否在当前的购物表中已经正常保存
        Shopcar dbCar = this.shopcarDAO.findByMidAndGid(shopcar.getMid(), shopcar.getGid());
        System.out.println(dbCar);
        if (dbCar == null) {    // 当前不存在购物数据
            // 2、此时表示用户是第一次进行购物车数据的处理（某件商品的第一次操作），购物车数量一定要设置为1
            shopcar.setAmount(1); // 第一次添加数量绝对就是1
            return this.shopcarDAO.doCreate(shopcar); // 实现数据的保存
        } else {    // 当前的商品数据已经添加过了
            return this.shopcarDAO.doEditAmount(shopcar.getMid(), shopcar.getGid(), dbCar.getAmount() + 1);
        }
    }

    @Override
    public Map<String, Object> list(String mid) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("allGoods", this.shopcarDAO.findAllGoodsByMid(mid)); // 查询购物车中完整的商品信息
        result.put("shopcar", this.shopcarDAO.findAllByMid(mid)); // 购物车数量
        return result;
    }
}
