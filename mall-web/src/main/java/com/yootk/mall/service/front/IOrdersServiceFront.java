package com.yootk.mall.service.front;

import java.util.Map;
import java.util.Set;

public interface IOrdersServiceFront { // 订单业务接口
    /**
     * 创建一个订单显示的业务处理方法
     * @param mid 要显示订单的用户ID
     * @param gids 要进行商品数据加载以及购物车数据加载的商品编号
     * @return 此时会返回有商品集合以及对应的购物车的数据内容，具体数据信息如下：
     * 1、key = allGoods、value = 要购买的商品集合；
     * 2、key = shopcar、value = 购物车的商品数量
     * @throws Exception
     */
    public Map<String, Object> preAdd(String mid, Set<Long> gids) throws Exception;
}
