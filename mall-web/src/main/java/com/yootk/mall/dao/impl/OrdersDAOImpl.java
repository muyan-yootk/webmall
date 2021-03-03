package com.yootk.mall.dao.impl;

import com.yootk.common.dao.abs.AbstractDAO;
import com.yootk.common.mvc.annotation.Repository;
import com.yootk.mall.dao.IOrdersDAO;
import com.yootk.mall.vo.Orders;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
@Repository
public class OrdersDAOImpl extends AbstractDAO implements IOrdersDAO {
    @Override
    public Long getInsertId() throws SQLException {
        return super.handleAutoIncrementKey();
    }

    @Override
    public boolean doCreate(Orders orders) throws SQLException {
        String sql = "INSERT INTO orders(mid, pid, cid, subdate, price, note, name, phone, address) VALUES (?,?,?,?,?,?,?,?,?)";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, orders.getMid());
        super.pstmt.setLong(2, orders.getPid());
        super.pstmt.setLong(3, orders.getCid());
        super.pstmt.setDate(4, new java.sql.Date(orders.getSubdate().getTime()));
        super.pstmt.setDouble(5, orders.getPrice());
        super.pstmt.setString(6, orders.getNote());
        super.pstmt.setString(7, orders.getName());
        super.pstmt.setString(8, orders.getPhone());
        super.pstmt.setString(9, orders.getAddress());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doEdit(Orders orders) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> longs) throws SQLException {
        return false;
    }

    @Override
    public Orders findById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Orders> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Orders> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Orders> findSplit(Integer currentPage, Integer lineSize, String column, String keyword) throws SQLException {
        return null;
    }

    @Override
    public Long getAllCount() throws SQLException {
        return null;
    }

    @Override
    public Long getAllCount(String column, String keyword) throws SQLException {
        return null;
    }
}
