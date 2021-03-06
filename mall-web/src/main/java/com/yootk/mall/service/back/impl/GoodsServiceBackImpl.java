package com.yootk.mall.service.back.impl;

import com.yootk.common.mvc.annotation.Aspect;
import com.yootk.common.mvc.annotation.Autowired;
import com.yootk.common.mvc.annotation.Service;
import com.yootk.common.service.abs.AbstractService;
import com.yootk.mall.dao.IGoodsDAO;
import com.yootk.mall.service.back.IGoodsServiceBack;
import com.yootk.mall.vo.Goods;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Aspect
public class GoodsServiceBackImpl extends AbstractService implements IGoodsServiceBack {
    @Autowired
    private IGoodsDAO goodsDAO ;
    @Override
    public boolean add(Goods vo) throws Exception {
        return this.goodsDAO.doCreate(vo);
    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (super.checkEmpty(column, keyWord)) { // 需要进行模糊查询
            map.put("allGoods", this.goodsDAO.findSplit(currentPage, lineSize, column, keyWord));
            map.put("allRecorders", this.goodsDAO.getAllCount(column, keyWord));
        } else {
            map.put("allGoods", this.goodsDAO.findSplit(currentPage, lineSize));
            map.put("allRecorders", this.goodsDAO.getAllCount());
        }
        return map;
    }

    @Override
    public Goods editPre(long gid) throws Exception {
        return this.goodsDAO.findById(gid);
    }

    @Override
    public boolean edit(Goods vo) throws Exception {
        return this.goodsDAO.doEdit(vo);
    }

    @Override
    public Map<String, Object> delete(Set<Long> gids) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("photos", this.goodsDAO.findPhotoNameByGid(gids)); // 获取要删除的全部图片
        map.put("flag", this.goodsDAO.doRemove(gids)); // 数据删除
        return map;
    }

}
