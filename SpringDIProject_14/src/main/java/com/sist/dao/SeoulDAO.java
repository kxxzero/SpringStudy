package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;

// 메모리 할당
@Repository("sDao")
public class SeoulDAO {
	// 구현된 mapper(스프링 내부에서 구현)
	@Autowired
	private SeoulMapper mapper;
	
	
	public List<SeoulVO> seoulListData(String table_name){
		return mapper.seoulListData(table_name);
	}
	
	
	public SeoulVO seoulDetailData(String table_name, int no) {
		return mapper.seoulDetailData(table_name, no);
	}
}
