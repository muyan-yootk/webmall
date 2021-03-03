package com.yootk.mall.dao;

import com.yootk.common.dao.base.IBaseDAO;
import com.yootk.mall.vo.Orders;

import java.sql.SQLException;

public interface IOrdersDAO extends IBaseDAO<Long, Orders> {
    public Long getInsertId() throws SQLException; // 获取最后一次增长ID
}
