package com.yootk.mall.service.front;

import com.yootk.mall.vo.Shopcar;

public interface IShopcarServiceFront { // 前台购物车业务
    /**
     * 用户在添加购物车时所使用的业务处理方法
     * @param shopcar 包含用户购物车操作的信息
     * @return 保存的状态
     * @throws Exception
     */
    public boolean add(Shopcar shopcar) throws Exception;
}
