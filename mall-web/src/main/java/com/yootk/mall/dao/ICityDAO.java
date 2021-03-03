package com.yootk.mall.dao;

import com.yootk.common.dao.base.IBaseDAO;
import com.yootk.mall.vo.City;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO extends IBaseDAO<Long, City> {
    /**
     * 根据省份编号查询所有的城市信息
     * @param pid 省份ID
     * @return 第一个省份下对应的所有的城市列表集合
     * @throws SQLException
     */
    public List<City> findAllByProvince(Long pid) throws SQLException;
}
