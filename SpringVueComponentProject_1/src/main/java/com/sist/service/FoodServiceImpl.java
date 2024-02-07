package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired // 메모리가 할당된 주소값을 얻어옴
	private FoodDAO dao;

	@Override
	public List<FoodVO> foodListData(int start, int end) {
		return dao.foodListData(start, end);
	}

	@Override
	public int foodTotalPage() {
		return dao.foodTotalPage();
	}

}
