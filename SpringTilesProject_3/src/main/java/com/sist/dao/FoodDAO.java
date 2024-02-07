package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	// 목록 출력
	public List<FoodVO> foodListData(int start, int end) {
		return mapper.foodListData(start, end);
	}
	
	// 총 페이지 수
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
}
