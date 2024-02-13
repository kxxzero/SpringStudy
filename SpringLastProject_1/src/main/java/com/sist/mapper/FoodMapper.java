package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodVO;

public interface FoodMapper {
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM food_menu_house "
			+ "WHERE address LIKE '%'||#{address}||'%' "
			+ "ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_menu_house "
			+ "WHERE address LIKE '%'||#{address}||'%'")
	public int foodFindCount(Map map);
	
	
	@Select("SELECT fno, score, poster, name, type, address, phone, theme, price, time, seat "
			+ "FROM food_menu_house "
			+ "WHERE fno=#{fno}")
	public FoodVO FoodDetailData(int fno);
}
