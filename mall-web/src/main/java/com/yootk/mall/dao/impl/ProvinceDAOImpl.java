package com.yootk.mall.dao.impl;

import com.yootk.common.dao.abs.AbstractDAO;
import com.yootk.common.mvc.annotation.Repository;
import com.yootk.mall.dao.IProvinceDAO;
import com.yootk.mall.vo.Province;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Repository
public class ProvinceDAOImpl extends AbstractDAO implements IProvinceDAO {
    @Override
    public boolean doCreate(Province province) throws SQLException {
        return false;
    }

    @Override
    public boolean doEdit(Province province) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> longs) throws SQLException {
        return false;
    }

    @Override
    public Province findById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Province> findAll() throws SQLException {
        String sql = "SELECT pid,title FROM province";
        super.pstmt = super.connection.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        return super.handleResultToList(rs, Province.class);
    }

    @Override
    public List<Province> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Province> findSplit(Integer currentPage, Integer lineSize, String column, String keyword) throws SQLException {
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
