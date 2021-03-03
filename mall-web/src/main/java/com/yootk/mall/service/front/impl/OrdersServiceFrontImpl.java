package com.yootk.mall.service.front.impl;

import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.service.abs.AbstractService;
import com.yootk.mall.dao.IGoodsDAO;
import com.yootk.mall.dao.IProvinceDAO;
import com.yootk.mall.dao.IShopcarDAO;
import com.yootk.mall.service.front.IOrdersServiceFront;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Aspect
public class OrdersServiceFrontImpl extends AbstractService implements IOrdersServiceFront {
    @Autowired
    private IGoodsDAO goodsDAO;
    @Autowired
    private IShopcarDAO shopcarDAO;
    @Autowired
    private IProvinceDAO provinceDAO;
    @Override
    public Map<String, Object> preAdd(String mid, Set<Long> gids) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("allGoods", this.goodsDAO.findByIds(gids));
        map.put("shopcar", this.shopcarDAO.findAllByMidAndGids(mid, gids));
        map.put("allProvinces", this.provinceDAO.findAll());
        return map;
    }
}
