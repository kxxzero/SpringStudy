package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.ReserveMapper;
import com.sist.vo.FoodVO;
import com.sist.vo.ReserveVO;

@Repository
public class ReserveDAO {
	@Autowired
	private ReserveMapper mapper;

	public List<FoodVO> foodReserveData(String type) {
		return mapper.foodReserveData(type);
	}

	public void foodReserveInsert(ReserveVO vo) {
		mapper.foodReserveInsert(vo);
	}

	public List<ReserveVO> reserveMyPageData(String userId) {
		return mapper.reserveMyPageData(userId);
	}
	
	public void reserveCancel(int rno) {
		mapper.reserveCancel(rno);
	}
	
	public List<ReserveVO> reserveAdminPageData(String userId) {
		return mapper.reserveAdminPageData(userId);
	}
	
	public void reserveOk(int no) {
		mapper.reserveOk(no);
	}
}
