package com.yootk.mall.service.front.impl;

import java.util.HashMap;
import java.util.Map;

import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.service.abs.AbstractService;
import com.yootk.mall.dao.IGoodsDAO;
import com.yootk.mall.service.front.IGoodsServiceFront;

@Service
@Aspect
public class GoodsServiceFrontImpl extends AbstractService implements IGoodsServiceFront {
    @Autowired
    private IGoodsDAO goodsDAO;

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (super.checkEmpty(column, keyWord)) { // 需要进行模糊查询
            map.put("allGoods", this.goodsDAO.findSplit(currentPage, lineSize, column, keyWord));
            map.put("allRecorders", this.goodsDAO.getAllCount(column, keyWord));
        } else {
            map.put("allGoods", this.goodsDAO.findSplit(currentPage, lineSize));
            map.put("allRecorders", this.goodsDAO.getAllCount());
        }
        return map;
    }

}
