package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodFindData(Map map) {
		return mapper.foodFindData(map);
	}
	
	public int foodFindCount(Map map) {
		return mapper.foodFindCount(map);
	}
	
	public FoodVO FoodDetailData(int fno) {
		return mapper.FoodDetailData(fno);
	}

}
