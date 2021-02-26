package com.yootk.mall.service.front;

import java.util.Map;

public interface IGoodsServiceFront {
	/**
	 * 进行商品信息的分页数据查询，如果没有查询列或查询关键字则进行整体查询
	 * @param currentPage 当前页
	 * @param lineSize 每页行
	 * @param column 查询列
	 * @param keyWord 查询关键字
	 * @return 返回的内容包含有如下信息：
	 * 1、key = allGoods、value = 全部商品信息；
	 * 2、key = allRecorders、value = 统计结果。
	 * 3、key = allItems、value = 全部的分类信息（Map集合）
	 * @throws Exception SQL
	 */
	public Map<String, Object> list(int currentPage, int lineSize,String column, String keyWord) throws Exception;
}
