package com.yootk.mall.dao;

import com.yootk.common.dao.base.IBaseDAO;
import com.yootk.mall.vo.Goods;

import java.sql.SQLException;
import java.util.Set;

public interface IGoodsDAO extends IBaseDAO<Long, Goods> {
    /**
     * 根据配置的gid编号集合获取对应的全部图片的名称
     * @param gids 编号的集合
     * @return 返回图片信息的集合
     * @throws SQLException SQL查询异常
     */
	public Set<String> findPhotoNameByGid(Set<Long> gids) throws SQLException;
}
