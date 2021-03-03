package com.yootk.mall.dao.impl;

import com.yootk.common.dao.abs.AbstractDAO;
import com.yootk.common.mvc.annotation.Repository;
import com.yootk.mall.dao.IOrdersDAO;
import com.yootk.mall.vo.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Repository
public class OrdersDAOImpl extends AbstractDAO implements IOrdersDAO {
    @Override
    public Long getInsertId() throws SQLException {
        return super.handleAutoIncrementKey();
    }

    @Override
    public List<Orders> findSplitByMid(String mid, Integer currentPage, Integer lineSize) throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        String sql = "SELECT oid,mid, pid, cid, subdate, price, note, name, phone, address FROM orders WHERE mid=? LIMIT ?,?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        super.pstmt.setInt(2, (currentPage - 1) * lineSize);
        super.pstmt.setInt(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Orders orders = new Orders();
            orders.setOid(rs.getLong(1));
            orders.setMid(rs.getString(2));
            orders.setPid(rs.getLong(3));
            orders.setCid(rs.getLong(4));
            orders.setSubdate(rs.getDate(5));
            orders.setPrice(rs.getDouble(6));
            orders.setNote(rs.getString(7));
            orders.setName(rs.getString(8));
            orders.setPhone(rs.getString(9));
            orders.setAddress(rs.getString(10));
            ordersList.add(orders);
        }
        return ordersList;
    }

    @Override
    public Long getAllCountByMid(String mid) throws SQLException {
        String sql = "SELECT COUNT(*) FROM orders WHERE mid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }

    @Override
    public Orders findByIdAndMid(Long oid, String mid) throws SQLException {
        String sql = "SELECT oid,mid, pid, cid, subdate, price, note, name, phone, address FROM orders WHERE oid=? AND mid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setLong(1, oid);
        super.pstmt.setString(2, mid);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            Orders orders = new Orders();
            orders.setOid(rs.getLong(1));
            orders.setMid(rs.getString(2));
            orders.setPid(rs.getLong(3));
            orders.setCid(rs.getLong(4));
            orders.setSubdate(rs.getDate(5));
            orders.setPrice(rs.getDouble(6));
            orders.setNote(rs.getString(7));
            orders.setName(rs.getString(8));
            orders.setPhone(rs.getString(9));
            orders.setAddress(rs.getString(10));
            return orders;
        }
        return null;
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
        String sql = "SELECT oid,mid, pid, cid, subdate, price, note, name, phone, address FROM orders";
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
