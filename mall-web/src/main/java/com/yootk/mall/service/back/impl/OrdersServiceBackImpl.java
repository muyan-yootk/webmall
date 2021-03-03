package com.yootk.mall.service.back.impl;

import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.service.abs.AbstractService;
import com.yootk.mall.dao.IOrdersDAO;
import com.yootk.mall.service.back.IOrdersServiceBack;

import java.util.HashMap;
import java.util.Map;

@Service
@Aspect
public class OrdersServiceBackImpl extends AbstractService implements IOrdersServiceBack {
    @Autowired
    private IOrdersDAO ordersDAO;
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
}
