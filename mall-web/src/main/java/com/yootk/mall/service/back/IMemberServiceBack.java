package com.yootk.mall.service.back;

import java.util.Map;

public interface IMemberServiceBack {
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;
}
