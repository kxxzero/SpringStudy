package com.sist.mapper;
/*
 * name => N
 * address => A
 * type => T
 * name+address => NA
 * name+type => NT
 * address+type => AT
 * name+address+type => NAT
 */

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.*;
public interface FoodMapper {
	
	/*
	 * XML
	 * 
	 * 	<trim prefix="(" suffix=")" prefixOverrides="(" suffixOverrides=")">
	 * 		  (접두어)     (접미어)
	 * 		=> 추가 / 제거
	 * 		name LIKE '%'||#{ss}||'%'
	 * 	</trim>
	 * 		name LIKE '%'||#{ss}||'%'
	 * 
	 * 	<foreach collection=\"fsArr\" item=\"fd\">
	 * 						
	 */
	
	@Select("<script>"
			+"SELECT fno, name, type, address "
			+"FROM food_menu_house "
			+"WHERE "
			+ "<trim prefixOverrides=\"OR\">"
			+ "<foreach collection=\"fsArr\" item=\"fd\">"
			+ "<choose>"
			+ "when test=\"ss=='N'.toString()\">"
			+ "OR name LIKE '%'||#{ss}||'%'"
			+ "</when>"
			+ "when test=\"ss=='N'.toString()\">"
			+ "OR address LIKE '%'||#{ss}||'%'"
			+ "</when>"
			+ "when test=\"ss=='N'.toString()\">"
			+ "OR type LIKE '%'||#{ss}||'%'"
			+ "</when>"
			+ "</choose>"
			+ "</foreach>"
			+ "</trim>"
			+ "</script>") // (name LIKE '%'||#{ss}||'%') OR (address LIKE '%'||#{ss}||'%')
	public List<FoodVO> foodFindData(Map map);
	
	
}
