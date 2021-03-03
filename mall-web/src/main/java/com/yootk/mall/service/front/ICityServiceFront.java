package com.yootk.mall.service.front;

import com.yootk.mall.vo.City;
import com.yootk.mall.vo.Province;

import java.util.List;

public interface ICityServiceFront {
    public List<City> list(Long pid) throws Exception;
}
