package com.yootk.mall.dao;

import com.yootk.common.dao.base.IBaseDAO;
import com.yootk.mall.vo.Details;

import java.util.List;

public interface IDetailsDAO extends IBaseDAO<Long, Details> {
    public boolean addBatch(List<Details>details) throws Exception;
}
