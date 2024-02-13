package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;

public class FreeBoardDAO {
	@Autowired
	private FreeBoardMapper mapper;
	
	public List<FreeBoardVO> freeboardListData(int start, int end) {
		return mapper.freeboardTotal
	}
}
