package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

/* MyBatis에서 자동 구현
	메소드 생성 시 자동 SQL 생성
	=> JPA(자동 생성)
		findByNo(int no) => WHERE no=?
		findByNameLike => WHERE name LIKE
		
	insert update delete findAll(Page page)
*/

import com.sist.dao.*;

public interface FoodMapper {
	/*
	 * <MySQL 버전>
	 * 	SELECT fno, name, poster, FROM food_menu_house
	 * 	ORDER BY fno ASC
	 * 	LIMIT #{start}, 20 // 시작 번호부터 20개를 가져옴
	 */
	
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM food_menu_house ORDER BY fno)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	
	public List<FoodVO> foodListData(Map map);
	
	
	@Select("SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM food_menu_house")
	public int foodTotalPage();
	
}
