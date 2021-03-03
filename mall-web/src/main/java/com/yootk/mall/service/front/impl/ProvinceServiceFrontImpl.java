package com.yootk.mall.service.front.impl;

import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.service.abs.AbstractService;
import com.yootk.mall.dao.IProvinceDAO;
import com.yootk.mall.service.front.IProvinceServiceFront;
import com.yootk.mall.vo.Province;

import java.util.List;

@Service
@Aspect
public class ProvinceServiceFrontImpl extends AbstractService implements IProvinceServiceFront {
    @Autowired
    private IProvinceDAO provinceDAO;
    @Override
    public List<Province> list() throws Exception {
        return this.provinceDAO.findAll();
    }
}
