package com.yootk.mall.service.front;

import com.yootk.mall.vo.Orders;

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

    /**
     * 实现订单的创建
     * @param orders 订单完整信息内容
     * @param gids 要进行订单详情添加的商品编号，同时也是删除购物车中数据的重要依据
     * @return 增加成功返回true，否则返回false
     * @throws Exception
     */
    public boolean add(Orders orders, Set<Long> gids) throws Exception;

    /**
     * 实现订单数据的分页查询
     * @param mid 当前的用户ID
     * @param currentPage 当前页
     * @param lineSize 每页行数
     * @return 订单的集合信息，包括有如下的内容：
     * 1、key = allOrders、value = 订单集合；
     * 2、key = allRecorders、value = 订单的数量
     * @throws Exception
     */
    public Map<String, Object> list(String mid, int currentPage, int lineSize) throws Exception;

    /**
     * 实现订单详情的查看
     * @param mid 当前的用户ID
     * @param oid 订单编号
     * @return 所有订单详情的信息，包括如下内容：
     * 1、key = orders、value = 单个订单的数据；
     * 2、key = allGoods、value = 订单商品信息；
     * 3、key = details、value = Map集合，描述订单商品编号以及数量
     * @throws Exception
     */
    public Map<String, Object> getDetails(String mid, long oid) throws Exception;
}
