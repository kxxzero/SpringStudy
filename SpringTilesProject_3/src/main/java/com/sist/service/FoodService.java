package com.sist.service;

import java.util.List;

import com.sist.vo.FoodVO;

public interface FoodService {
	// 목록 출력
	public List<FoodVO> foodListData(int start, int end);
	
	// 총 페이지 수
	public int foodTotalPage();
}
