package com.yootk.mall.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yootk.common.dao.abs.AbstractDAO;
import com.yootk.common.mvc.annotation.Repository;
import com.yootk.mall.dao.IGoodsDAO;
import com.yootk.mall.vo.Goods;

@Repository
public class GoodsDAOImpl extends AbstractDAO implements IGoodsDAO {

    @Override
    public boolean doCreate(Goods vo) throws SQLException {
        String sql = "INSERT INTO goods(name,price,photo) VALUES (?,?,?)";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, vo.getName());
        super.pstmt.setDouble(2, vo.getPrice());
        super.pstmt.setString(3, vo.getPhoto());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doEdit(Goods vo) throws SQLException {
        String sql = "UPDATE goods SET name=?,price=?,photo=? WHERE gid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, vo.getName());
        super.pstmt.setDouble(2, vo.getPrice());
        super.pstmt.setString(3, vo.getPhoto());
        super.pstmt.setLong(4, vo.getGid());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doRemove(Set<Long> ids) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Goods findById(Long id) throws SQLException {
        String sql = "SELECT gid,name,price,photo FROM goods WHERE gid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setLong(1, id);
        return super.handleResultToVO(super.pstmt.executeQuery(), Goods.class);
    }

    @Override
    public List<Goods> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Goods> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        List<Goods> all = new ArrayList<Goods>();
        String sql = "SELECT gid,name,price,photo FROM goods LIMIT " + (currentPage - 1) * lineSize + "," + lineSize;
        super.pstmt = super.connection.prepareStatement(sql);
        return super.handleResultToList(super.pstmt.executeQuery(), Goods.class);
    }

    @Override
    public List<Goods> findSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
        List<Goods> all = new ArrayList<Goods>();
        String sql = "SELECT gid,name,price,photo FROM goods WHERE " + column + " LIKE ? LIMIT " + (currentPage - 1) * lineSize + "," + lineSize;
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        return super.handleResultToList(super.pstmt.executeQuery(), Goods.class);
    }

    @Override
    public Long getAllCount() throws SQLException {
        return super.countHandle("goods");
    }

    @Override
    public Long getAllCount(String column, String keyWord) throws SQLException {
        return super.countHandle("goods", column, keyWord);
    }

}
