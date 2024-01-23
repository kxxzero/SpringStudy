package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public class SeoulDAO {
	private SeoulMapper mapper;
	public void setMapper(SeoulMapper mapper) {
		this.mapper=mapper;
	}
	
	public List<SeoulVO> natureListData(){
		return mapper.natureListData();
	}

	public SeoulVO natureDetailData(int no) {
		return mapper.natureDetailData(no);
	}
	
	public List<SeoulVO> natureFindData(String title) {
		return mapper.natureFindData(title);
	}
}
