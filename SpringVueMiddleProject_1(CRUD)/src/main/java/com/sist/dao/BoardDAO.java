package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class BoardDAO {
	@Autowired// 스프링에게 구현된 메소드를 찾아 옴
	private BoardMapper mapper;
	
	// 목록 출력
	public List<BoardVO> boardListData(int start, int end){
		return mapper.boardListData(start, end);
	}
	
	// 총 페이지
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	// 작성
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	// 상세보기
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	// 수정
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	
	/*  UPDATE, INSERT, DELETE => DAO는 Mapper와 달리 메소드의 데이터형을 void가 아닌 자유롭게 지정할 수 있음
	 */
	
	public String boardUpdate(BoardVO vo) {
		String result="no";
		String db_pwd=mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			result="yes";
			mapper.boardUpdate(vo);
		}
		
		return result;
	}
	
	// 삭제
	public String boardDelete(int no, String pwd) {
		String result="no";
		String db_pwd=mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			result="yes";
			mapper.boardDelete(no);
		}
		return result;
	}
}
