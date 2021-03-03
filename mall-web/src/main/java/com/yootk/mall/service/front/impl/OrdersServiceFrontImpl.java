package com.yootk.mall.service.front.impl;

import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.service.abs.AbstractService;
import com.yootk.mall.dao.*;
import com.yootk.mall.service.front.IOrdersServiceFront;
import com.yootk.mall.vo.Details;
import com.yootk.mall.vo.Goods;
import com.yootk.mall.vo.Orders;
import com.yootk.mall.vo.Shopcar;

import java.util.*;

@Service
@Aspect
public class OrdersServiceFrontImpl extends AbstractService implements IOrdersServiceFront {
    @Autowired
    private IGoodsDAO goodsDAO;
    @Autowired
    private IShopcarDAO shopcarDAO;
    @Autowired
    private IProvinceDAO provinceDAO;
    @Autowired
    private IOrdersDAO ordersDAO;
    @Autowired
    private IDetailsDAO detailsDAO;
    @Override
    public Map<String, Object> preAdd(String mid, Set<Long> gids) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allGoods", this.goodsDAO.findByIds(gids));
        map.put("shopcar", this.shopcarDAO.findAllByMidAndGids(mid, gids));
        map.put("allProvinces", this.provinceDAO.findAll());
        return map;
    }

    @Override
    public boolean add(Orders orders, Set<Long> gids) throws Exception {
        // 1、需要进行订单的创建，但是订单创建的时候对于订单的总金额需要进行计算
        // 而要想进行金额的计算就必须获取商品信息以及对应的购买数量信息
        List<Goods> goodsList = this.goodsDAO.findByIds(gids); // 所有的商品信息
        // Map集合之中的key = 商品ID、value = 购买数量
        Map<Long, Integer> shopcar = this.shopcarDAO.findAllByMidAndGids(orders.getMid(), gids);
        Double allPrice = 0.0; // 按照正常的电商平台设计，此时应该是整数
        for (Goods goods : goodsList) {
            allPrice += goods.getPrice() * shopcar.get(goods.getGid()); // 计算总价
        }
        // 2、将对应的业务的信息保存在订单表之中
        orders.setPrice(allPrice); // 设置订单的总价
        orders.setSubdate(new Date()); // 订单提交日期为当前日期时间
        // 3、首先要进行订单的存储，因为只有订单存储之后才可以获取订单的ID
        if (this.ordersDAO.doCreate(orders)) {  // 成功保存订单
            // 4、订单成功保存之后就可以获取到当前的订单ID
            long oid = this.ordersDAO.getInsertId(); // 获取增长后的订单ID
            List<Details> detailsList = new ArrayList<>();
            List<Shopcar> deleteShopcarList = new ArrayList<>();
            for (Goods goods : goodsList) { // 保存订单详情
                Details details = new Details();
                details.setGid(goods.getGid()); // 商品编号
                details.setOid(oid); // 详情所属的订单信息
                details.setAmount(shopcar.get(goods.getGid()));
                detailsList.add(details);
                Shopcar car = new Shopcar(); // 为了删除购物车准备的集合
                car.setMid(orders.getMid());
                car.setGid(goods.getGid());
                deleteShopcarList.add(car);
            }
            // 5、进行订单批量数据的存储
            if (this.detailsDAO.addBatch(detailsList)) {    // 存储成功
                // 6、删除购物车中的数据内容
                return this.shopcarDAO.doRemoveByMidAndGidBatch(deleteShopcarList);
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> list(String mid, int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allOrders", this.ordersDAO.findSplitByMid(mid, currentPage, lineSize));
        map.put("allRecorders", this.ordersDAO.getAllCountByMid(mid));
        return map;
    }

    @Override
    public Map<String, Object> getDetails(String mid, long oid) throws Exception {
        Orders orders = this.ordersDAO.findByIdAndMid(oid, mid); // 查询单个订单信息
        List<Details> allDetails = this.detailsDAO.findAllByOrders(oid); // 订单详情
        // key为商品ID、value为商品的数量
        Map<Long, Integer> detailsMap = new HashMap<>();
        for (Details details : allDetails) {
            detailsMap.put(details.getGid(), details.getAmount());
        }
        // 随后通过商品的ID集合查询所有的商品数据
        List<Goods> allGoods = this.goodsDAO.findByIds(detailsMap.keySet());
        Map<String, Object> map = new HashMap<>();
        map.put("allGoods", allGoods);
        map.put("orders", orders);
        map.put("details", detailsMap);
        return map;
    }
}
