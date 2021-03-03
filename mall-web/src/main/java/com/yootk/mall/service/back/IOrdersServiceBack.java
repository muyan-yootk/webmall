package com.yootk.mall.service.back;

import java.util.Map;

public interface IOrdersServiceBack {
    public Map<String, Object> list(String column, String keyword, Integer currentPage, Integer lineSize) throws Exception;
}
