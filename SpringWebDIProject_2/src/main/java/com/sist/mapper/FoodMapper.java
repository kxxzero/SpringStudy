package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.dao.*;

// 제어

public interface FoodMapper {
	@Select("SELECT fno, poster, name, num " // num이 있을 경우 페이지 나눠짐
			+ "FROM (SELECT fno, poster, name, rownum as num "
			+ "FROM (SELECT fno, poster, name "
			+ "FROM food_menu_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end); // 매개 변수가 2개 이상일 때느 @Param 사용
	
	
	@Select("SELECT CEIL(COUNT(*)/12.0) "
			+ "FROM food_menu_house")
	public int foodTotalPage();
	
	
	@Select("SELECT fno, name, type, address, price, time, content, poster "
			+ "FROM food_menu_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	
	@Select("SELECT fno, poster, name, num " // num이 있을 경우 페이지 나눠짐
			+ "FROM (SELECT fno, poster, name, rownum as num "
			+ "FROM (SELECT fno, poster, name "
			+ "FROM food_menu_house "
			+ "WHERE address LIKE '%'||#{address}||'%' "
			+ "ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindData(Map map);
	
	
	@Select("SELECT CEIL((COUNT(*)/12.0) "
			+ "FROM food_menu_house "
			+ "WHERE address LIKE '%'||#{address}||'%'")
	public int foodFindTotalPage(String address);
	
}
