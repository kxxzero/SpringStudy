package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

//스프링에 관리 요청 => interface, VO => 메모리 할당 불가 => 스프링 관리 불가
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper; 
	
	public List<FoodVO> foodListData(int start, int end) {
		return mapper.foodListData(start, end);
	}
	
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
}
