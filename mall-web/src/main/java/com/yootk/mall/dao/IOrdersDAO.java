package com.yootk.mall.dao;

import com.yootk.common.dao.base.IBaseDAO;
import com.yootk.mall.vo.Orders;

import java.sql.SQLException;
import java.util.List;

public interface IOrdersDAO extends IBaseDAO<Long, Orders> {
    public Long getInsertId() throws SQLException; // 获取最后一次增长ID
    public List<Orders> findSplitByMid(String mid, Integer currentPage, Integer lineSize) throws SQLException;
    public Long getAllCountByMid(String mid) throws SQLException;
    public Orders findByIdAndMid(Long oid, String mid) throws SQLException;
}
