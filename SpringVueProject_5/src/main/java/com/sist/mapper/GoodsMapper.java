package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.*;

public interface GoodsMapper {
	@Select("SELECT no, goods_discount, hit, goods_name, goods_sub, goods_price, goods_first_price, goods_delivery, goods_poster, num "
			+ "FROM (SELECT no, goods_discount, hit, goods_name, goods_sub, goods_price, goods_first_price, goods_delivery, goods_poster, rownum as num "
			+ "FROM (SELECT no, goods_discount, hit, goods_name, goods_sub, goods_price, goods_first_price, goods_delivery, goods_poster "
			+ "FROM goods_all ORDER BY no)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(Map map);
			
	@Select("SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM goods_all")
	public int goodsTotalPage();
}
