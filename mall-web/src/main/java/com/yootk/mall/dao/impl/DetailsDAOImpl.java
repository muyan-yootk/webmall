package com.yootk.mall.dao.impl;

import com.yootk.common.dao.abs.AbstractDAO;
import com.yootk.common.mvc.annotation.Repository;
import com.yootk.mall.dao.IDetailsDAO;
import com.yootk.mall.vo.Details;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
@Repository
public class DetailsDAOImpl extends AbstractDAO implements IDetailsDAO {
    @Override
    public boolean addBatch(List<Details> details) throws SQLException {
        String sql = "INSERT INTO details(oid, gid, amount) VALUES (?, ?, ?)";
        super.pstmt = super.connection.prepareStatement(sql);
        for (Details det : details) {
            super.pstmt.setLong(1, det.getOid());
            super.pstmt.setLong(2, det.getGid());
            super.pstmt.setInt(3, det.getAmount());
            super.pstmt.addBatch();
        }
        super.pstmt.executeBatch();
        return true;
    }

    @Override
    public List<Details> findAllByOrders(long oid) throws SQLException {
        String sql = "SELECT dtid,oid, gid, amount FROM details WHERE oid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setLong(1, oid);
        ResultSet rs = super.pstmt.executeQuery();
        return super.handleResultToList(rs, Details.class);
    }

    @Override
    public boolean doCreate(Details details) throws SQLException {
        return false;
    }

    @Override
    public boolean doEdit(Details details) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> longs) throws SQLException {
        return false;
    }

    @Override
    public Details findById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Details> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Details> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Details> findSplit(Integer currentPage, Integer lineSize, String column, String keyword) throws SQLException {
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
