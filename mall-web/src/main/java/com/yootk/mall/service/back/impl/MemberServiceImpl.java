package com.yootk.mall.service.back.impl;

import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.service.abs.AbstractService;
import com.yootk.mall.dao.IMemberDAO;
import com.yootk.mall.service.back.IMemberServiceBack;

import java.util.HashMap;
import java.util.Map;

@Service
@Aspect
public class MemberServiceImpl extends AbstractService implements IMemberServiceBack {
    @Autowired
    private IMemberDAO memberDAO;
    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (super.checkEmpty(column, keyWord)) { // 需要进行模糊查询
            map.put("allMembers", this.memberDAO.findSplit(currentPage, lineSize, column, keyWord));
            map.put("allRecorders", this.memberDAO.getAllCount(column, keyWord));
        } else {
            map.put("allMembers", this.memberDAO.findSplit(currentPage, lineSize));
            map.put("allRecorders", this.memberDAO.getAllCount());
        }
        return map;
    }
}
