package com.yootk.mall.service.front;

import com.yootk.mall.vo.Shopcar;

import java.util.Map;

public interface IShopcarServiceFront { // 前台购物车业务
    /**
     * 用户在添加购物车时所使用的业务处理方法
     * @param shopcar 包含用户购物车操作的信息
     * @return 保存的状态
     * @throws Exception
     */
    public boolean add(Shopcar shopcar) throws Exception;

    /**
     * 查询用户购物车列表的详细信息
     * @param mid 当前登录的用户ID
     * @return 返回有如下的两类数据内容：
     * 1、key = allGoods、value = 所有购物车的商品信息详情；
     * 2、key = shopcar、value = 购物商品数量
     * @throws Exception
     */
    public Map<String, Object> list(String mid) throws Exception;
}
