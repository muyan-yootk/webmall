package com.yootk.mall.service.front.impl;

import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.service.abs.AbstractService;
import com.yootk.mall.dao.ICityDAO;
import com.yootk.mall.service.front.ICityServiceFront;
import com.yootk.mall.vo.City;

import java.util.List;

@Service
@Aspect
public class CityServiceFrontImpl extends AbstractService implements ICityServiceFront {
    @Autowired
    private ICityDAO cityDAO;
    @Override
    public List<City> list(Long pid) throws Exception {
        return this.cityDAO.findAllByProvince(pid);
    }
}
