package com.yootk.mall.service.back.impl;

import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.service.abs.AbstractService;
import com.yootk.mall.dao.IDetailsDAO;
import com.yootk.mall.dao.IGoodsDAO;
import com.yootk.mall.dao.IOrdersDAO;
import com.yootk.mall.service.back.IOrdersServiceBack;
import com.yootk.mall.vo.Details;
import com.yootk.mall.vo.Goods;
import com.yootk.mall.vo.Orders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Aspect
public class OrdersServiceBackImpl extends AbstractService implements IOrdersServiceBack {
    @Autowired
    private IOrdersDAO ordersDAO;
    @Autowired
    private IDetailsDAO detailsDAO;
    @Autowired
    private IGoodsDAO goodsDAO;
    @Override
    public Map<String, Object> list(String column, String keyword, Integer currentPage, Integer lineSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (super.checkEmpty(column, keyword)) { // 需要进行模糊查询
            map.put("allOrders", this.ordersDAO.findSplit(currentPage, lineSize, column, keyword));
            map.put("allRecorders", this.ordersDAO.getAllCount(column, keyword));
        } else {
            map.put("allOrders", this.ordersDAO.findSplit(currentPage, lineSize));
            map.put("allRecorders", this.ordersDAO.getAllCount());
        }
        return map;
    }

    @Override
    public Map<String, Object> getDetails(long oid) throws Exception {
        Orders orders = this.ordersDAO.findById(oid); // 查询单个订单信息
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
