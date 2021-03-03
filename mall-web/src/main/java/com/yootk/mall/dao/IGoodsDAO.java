package com.yootk.mall.dao;

import com.yootk.common.dao.base.IBaseDAO;
import com.yootk.mall.vo.Goods;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IGoodsDAO extends IBaseDAO<Long, Goods> {
    /**
     * 根据配置的gid编号集合获取对应的全部图片的名称
     * @param gids 编号的集合
     * @return 返回图片信息的集合
     * @throws SQLException SQL查询异常
     */
	public Set<String> findPhotoNameByGid(Set<Long> gids) throws SQLException;

    /**
     * 根据给定的商品编号获取商品的完整列表内容
     * @param ids 所有要加载的商品编号集合
     * @return 即将下单的所有商品
     * @throws SQLException
     */
	public List<Goods> findByIds(Set<Long> ids) throws SQLException;
}
