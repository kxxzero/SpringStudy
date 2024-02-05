package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

@Repository // 구현된 매퍼를 사용하는 클래스 => 유지보수를 할 경우 다른 클래스에 영향  
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	// 목록 출력
	public List<FoodVO> foodListData(int start, int end) {
		return mapper.foodListData(start, end); 
	}
	
	// 총 페이지
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	
	// 상세보기
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
	
}
