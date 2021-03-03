package com.yootk.mall.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
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
        return super.handleRemove("goods", "gid", ids);
    }
    @Override
    public Set<String> findPhotoNameByGid(Set<Long> gids) throws SQLException {
        Set<String> set = new HashSet<>(); // 创建Set集合
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT photo FROM goods WHERE photo!=? AND gid in (");
        gids.forEach( gid ->
                sql.append(gid).append(",")
        );
        sql.delete(sql.length() - 1, sql.length()).append(")");
        super.pstmt = super.connection.prepareStatement(sql.toString());
        super.pstmt.setString(1, "nophoto.jpg");
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            set.add(rs.getString(1)); // 获取全部图片名称
        }
        return set;
    }

    @Override
    public List<Goods> findByIds(Set<Long> ids) throws SQLException {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT gid,name,price,photo FROM goods WHERE gid IN (");
        for (Long id : ids) {
            sql.append(id).append(",");
        }
        sql.delete(sql.length() - 1, sql.length()).append(")");
        super.pstmt = super.connection.prepareStatement(sql.toString());
        ResultSet rs = super.pstmt.executeQuery(); // 数据查询操作
        return super.handleResultToList(rs, Goods.class);
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
