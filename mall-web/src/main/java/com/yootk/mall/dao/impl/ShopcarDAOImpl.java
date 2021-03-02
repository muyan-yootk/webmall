package com.yootk.mall.dao.impl;

import com.yootk.common.dao.abs.AbstractDAO;
import com.yootk.common.mvc.annotation.Repository;
import com.yootk.mall.dao.IShopcarDAO;
import com.yootk.mall.vo.Goods;
import com.yootk.mall.vo.Shopcar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class ShopcarDAOImpl extends AbstractDAO implements IShopcarDAO {
    @Override
    public Shopcar findByMidAndGid(String mid, Long gid) throws SQLException {
        String sql = "SELECT scid, mid, gid, amount FROM shopcar WHERE mid=? AND gid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        super.pstmt.setLong(2, gid);
        ResultSet rs = super.pstmt.executeQuery();
        return super.handleResultToVO(rs, Shopcar.class);
    }

    @Override
    public boolean doEditAmount(String mid, Long gid, Integer amount) throws SQLException {
        String sql = "UPDATE shopcar SET amount=? WHERE mid=? AND gid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setInt(1, amount);
        super.pstmt.setString(2, mid);
        super.pstmt.setLong(3, gid);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doEditAmountBatch(List<Shopcar> cars) throws Exception {
        String sql = "UPDATE shopcar SET amount=? WHERE mid=? AND gid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        for (Shopcar car : cars) {
            super.pstmt.setInt(1, car.getAmount());
            super.pstmt.setString(2, car.getMid());
            super.pstmt.setLong(3, car.getGid());
            super.pstmt.addBatch(); // 增加批处理
        }
        super.pstmt.executeBatch(); // 执行批处理
        return true;
    }

    @Override
    public List<Goods> findAllGoodsByMid(String mid) throws SQLException {
        String sql = "SELECT gid,name,price,photo FROM goods " +
                " WHERE gid IN (SELECT gid FROM shopcar WHERE mid=?)";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        ResultSet rs = super.pstmt.executeQuery();
        return super.handleResultToList(rs, Goods.class);
    }

    @Override
    public Map<Long, Integer> findAllByMid(String mid) throws SQLException {
        Map<Long, Integer> map = new HashMap<>();
        String sql = "SELECT gid,amount FROM shopcar WHERE mid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) { // 循环结果集
            map.put(rs.getLong(1), rs.getInt(2)); // 保存购物车数据
        }
        return map;
    }

    @Override
    public boolean doRemoveByMidAndGid(String mid, Long gid) throws SQLException {
        String sql = "DELETE FROM shopcar WHERE mid=? AND gid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        super.pstmt.setLong(2, gid);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doRemoveByMidAndGidBatch(List<Shopcar> cars) throws SQLException {
        String sql = "DELETE FROM shopcar WHERE mid=? AND gid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        for (Shopcar car : cars) {
            super.pstmt.setString(1, car.getMid());
            super.pstmt.setLong(2, car.getGid());
            super.pstmt.addBatch();
        }
        super.pstmt.executeBatch();
        return true;
    }

    @Override
    public boolean doCreate(Shopcar shopcar) throws SQLException {
        String sql = "INSERT INTO shopcar(mid, gid, amount) VALUES (?, ?, ?)";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, shopcar.getMid());
        super.pstmt.setLong(2, shopcar.getGid());
        super.pstmt.setInt(3, shopcar.getAmount());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doEdit(Shopcar shopcar) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> longs) throws SQLException {
        return false;
    }

    @Override
    public Shopcar findById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Shopcar> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Shopcar> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Shopcar> findSplit(Integer currentPage, Integer lineSize, String column, String keyword) throws SQLException {
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
