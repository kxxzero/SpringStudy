package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository

public class DataBoardDAO {
	@Autowired
	private DataBoardMapper mapper;
	
	// 목록 출력
	public List<DataBoardVO> databoardListData(int start, int end){
		return mapper.databoardListData(start, end);
	}
	
	// 총 페이지
	public int databoardTotalPage() {
		return mapper.databoardTotalPage();
	}
	
	// 작성
	public void databoardInsert(DataBoardVO vo) {
		mapper.databoardInsert(vo);
	}
	
	// 수정
	public DataBoardVO databoardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
}
