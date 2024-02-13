package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.FreeBoardVO;

public interface FreeBoardMapper {

	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(projectFreeBoard pfb_no_pk)*/ no, subject, name, regdate "
			+ "FROM projectFreeBoard)) "
			+ "WHERE num BTEWEEN #{start} AND #{end}")
	public List<FreeBoardVO> freeboardListData(@Param("start") int start, @Param("end") int end);

	// 총 페이지
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM projectFreeBoard")
	public int freeboardTotalPage();
	
	// 추가
	@Insert("INSERT INTO projectFreeBoard(no, name, subject, content, pwd) "
			+ "VALUES(pfd_num_seq.nextval, #{name}, #{subject}, #{content}, #{pwd}")
	public void freeboardInsert(FreeBoardVO vo);
	
	// 상세보기
	@Update("UPDATE projectFreeBoard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public int hitIncrement(int no);
	
	// 수정
	@Select("SELECT no, name, subject, content, hit, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS) as dbday "
			+ "FROM projectFreeBoard "
			+ "WHERE no=#{NO}")
	public FreeBoardVO freeboardDetailData(int no);
	
	
}