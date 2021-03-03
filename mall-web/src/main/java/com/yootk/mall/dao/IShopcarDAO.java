package com.yootk.mall.dao;

import com.yootk.common.dao.base.IBaseDAO;
import com.yootk.mall.vo.Goods;
import com.yootk.mall.vo.Shopcar;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IShopcarDAO extends IBaseDAO<Long, Shopcar> {
    /**
     * 根据用户的ID以及商品ID查询出一个购物车的完整数据信息，通过此操作来判断该商品是否已经添加过
     * @param mid 当前的操作用户
     * @param gid 要添加购物车的商品ID
     * @return 如果现在可以发现有指定的内容，那么则返回VO实例，否则返回null
     * @throws SQLException SQL异常
     */
    public Shopcar findByMidAndGid(String mid, Long gid) throws SQLException;

    /**
     * 根据当前的用户保存的商品购物车的信息进行数量的更新
     * @param mid 用户的ID
     * @param gid 商品ID
     * @param amount 修改后的数量
     * @return 更新成功返回true，否则返回false
     * @throws SQLException SQL异常
     */
    public boolean doEditAmount(String mid, Long gid, Integer amount) throws SQLException;

    public boolean doEditAmountBatch(List<Shopcar> cars) throws Exception;

    /**
     * 根据用户查询出对应的所有商品信息
     * @param mid 用户的ID信息
     * @return 购物车的所有商品完整数据
     * @throws SQLException
     */
    public List<Goods> findAllGoodsByMid(String mid) throws SQLException;

    /**
     * 用户对应购物车中的商品数量内容
     * @param mid 用户ID
     * @return key为商品编号（gid）、value为商品的购物数量
     * @throws SQLException
     */
    public Map<Long, Integer> findAllByMid(String mid) throws SQLException;

    /**
     * 查询指定用户针对于特定的商品编号所定义的商品数量
     * @param mid 当前的用户id
     * @param gids 商品编号
     * @return key为商品编号（gid）、value为商品的购物数量
     * @throws SQLException
     */
    public Map<Long, Integer> findAllByMidAndGids(String mid, Set<Long> gids) throws SQLException;

    /**
     * 删除购物车之中的商品信息
     * @param mid 用户ID
     * @param gid 商品ID
     * @return 成功返回true，否则返回false
     * @throws SQLException
     */
    public boolean doRemoveByMidAndGid(String mid, Long gid) throws SQLException;

    /**
     * 数据的批量删除
     * @param cars 包含有mid以及gid数据内容
     * @return 删除成功返回true，否则返回false
     * @throws SQLException
     */
    public boolean doRemoveByMidAndGidBatch(List<Shopcar> cars) throws SQLException;
}
