package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.*;
public class SeoulMapper {
	@Select("SELECT no, title "
			+ "FROM ${table_name}")
	public List<SeoulVO> seoulListData(String table_name);
	
	@Select("SELECT no, title, msg, address "
			+ "FROM ${table_name} "
			+ "WHERE no=#{no}")
	public SeoulVO seoulDetailData(@Param("table_name") String table_name, @Param("no") int no); // mapper 안에 매개변수는 1개만 입력할 수 있는데 @Param 태그를 통해 2개 이상 입력 가능 => 3개 이상인 경우에는 map을 부여하면 됨
	
}
