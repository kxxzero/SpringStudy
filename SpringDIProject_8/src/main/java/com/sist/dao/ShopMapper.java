package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface ShopMapper {
	@Select("SELECT no, hit, title, poster, msg, address "
			+ "FROM seoul_shop "
			+ "ORDER BY no  ASC")
	public List<ShopVO> shopListData();
	
	@Select("SELECT no, hit, title, poster, msg, address "
			+ "FROM seoul_shop "
			+ "WHERE no=#{no}")
	public ShopVO ShopDetailData(int no);
	
	@Select("SELECT no, hit, title, poster, msg, address "
			+ "FROM seoul_shop "
			+ "WHERE title LIKE '%'||#{title}||'%'")
	public List<ShopVO> shopFindData(String title);
}
