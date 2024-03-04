package com.sist.service;

import java.util.List;

import com.sist.vo.FoodVO;
import com.sist.vo.ReserveVO;

public interface ReserveService {
	public List<FoodVO> foodReserveData(String type);

	public void foodReserveInsert(ReserveVO vo);

	public List<ReserveVO> reserveMyPageData(String userId);
	
	public void reserveCancel(int rno);
	
	public List<ReserveVO> reserveAdminPageData(String userId);
}
