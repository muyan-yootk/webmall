package com.yootk.mall.service.back;

import java.util.Map;

public interface IOrdersServiceBack {
    public Map<String, Object> list(String column, String keyword, Integer currentPage, Integer lineSize) throws Exception;
    /**
     * 实现订单详情的查看
     * @param oid 订单编号
     * @return 所有订单详情的信息，包括如下内容：
     * 1、key = orders、value = 单个订单的数据；
     * 2、key = allGoods、value = 订单商品信息；
     * 3、key = details、value = Map集合，描述订单商品编号以及数量
     * @throws Exception
     */
    public Map<String, Object> getDetails(long oid) throws Exception;
}
