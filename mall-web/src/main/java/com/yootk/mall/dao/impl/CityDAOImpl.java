package com.yootk.mall.dao.impl;

import com.yootk.common.dao.abs.AbstractDAO;
import com.yootk.common.mvc.annotation.Repository;
import com.yootk.mall.dao.ICityDAO;
import com.yootk.mall.vo.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
@Repository
public class CityDAOImpl extends AbstractDAO implements ICityDAO {
    @Override
    public List<City> findAllByProvince(Long pid) throws SQLException {
        String sql = "SELECT cid,title,pid FROM city WHERE pid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setLong(1, pid);
        ResultSet rs = super.pstmt.executeQuery();
        return super.handleResultToList(rs, City.class);
    }

    @Override
    public boolean doCreate(City city) throws SQLException {
        return false;
    }

    @Override
    public boolean doEdit(City city) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> longs) throws SQLException {
        return false;
    }

    @Override
    public City findById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<City> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<City> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<City> findSplit(Integer currentPage, Integer lineSize, String column, String keyword) throws SQLException {
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
