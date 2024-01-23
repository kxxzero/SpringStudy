package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.*;
public class FoodMapper {
	// 카테고리별 맛집
	@Select("SELECT fno, name, address, phone, type "
			+ "FROM food_house "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodCategoryListData(int cno);
	
	// 맛집 정보
	@Select("SELECT fno, name, address, score, phone, type, parking, time, menu "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
			
}
