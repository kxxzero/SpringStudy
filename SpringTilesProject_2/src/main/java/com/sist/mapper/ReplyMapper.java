package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.ReplyVO;

public interface ReplyMapper {
	@Select("SELECT rno, fno, id, name, subject, msg, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday "
			+ "FROM vueReply "
			+ "WHERE fno=#{fno} "
			+ "ORDER BY rno DESC")
	public List<ReplyVO> replyListData(int fno);
	
	@Insert("INSERT INRO vueReply VALUES("
			+ "vr_rno_seq.nextval, #{fno}, #{id}, #{name}, #{msg}, SYSDATE)")
	public void replyInsert(ReplyVO vo);
	
	// 수정
	
	// 삭제
	
	@Delete("DELETE FROM vueReply WHERE rno=#{rno}")
	public void replyDelete(int rno);
}
