package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface DataBoardMapper {
	// 목록 출력
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(vueDataBoard vdb_no_pk)*/ no, subject, name, regdate, hit "
			+ "FROM vueDataBoard)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(@Param("start") int start, @Param("end") int end);
	
	// 총 페이지
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM vueDataBoard")
	public int databoardTotalPage();
	
	// 작성
	@Insert("INSERT INTO vueDataBoard "
			+ "VALUES(vdb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd}, SYSDATE, 0, #{filename}, #{filesize}, #{filecount})")
	public void databoardInsert(DataBoardVO vo);

	// 수정
	@Update("UPDATE vueDataBoard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, filename, filesize, filecount "
			+ "FROM vueDataBoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
}
