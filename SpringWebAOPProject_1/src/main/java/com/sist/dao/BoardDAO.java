package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sist.mapper.*;
@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(Map map) {
		return mapper.boardListData(map);
	}
	
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}

	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no); // 조회수 증가
		return mapper.boardDetailData(no); // 상세보기
	}
	
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no); // 필요할 때마다 호출
	}
	
	// 섞어서 조립
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck=false;
		String db_pwd=mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd()))
		{
			bCheck=true;
			mapper.boardUpdate(vo);
		}
		return bCheck;
	}
	
	public List<BoardVO> boardTop5() {
		return mapper.boardTop5();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void boardReplyInsert(int pno, BoardVO vo) {
		BoardVO pvo=mapper.boardParentInfoData(pno);
		mapper.boardGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pno);
		mapper.boardReplyInsert(vo);
		mapper.boardDepthIncrement(pno);
	}
}
