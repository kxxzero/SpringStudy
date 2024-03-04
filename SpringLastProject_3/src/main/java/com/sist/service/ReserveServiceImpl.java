package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.ReserveDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.ReserveVO;

@Service
public class ReserveServiceImpl implements ReserveService {

	@Autowired
	private ReserveDAO rDao;

	@Override
	public List<FoodVO> foodReserveData(String type) {
		// TODO Auto-generated method stub
		return rDao.foodReserveData(type);
	}

	@Override
	public void foodReserveInsert(ReserveVO vo) {
		// TODO Auto-generated method stub
		rDao.foodReserveInsert(vo);
	}

	@Override
	public List<ReserveVO> reserveMyPageData(String userId) {
		// TODO Auto-generated method stub
		return rDao.reserveMyPageData(userId);
	}

	@Override
	public void reserveCancel(int rno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReserveVO> reserveAdminPageData(String userId) {
		// TODO Auto-generated method stub
		return rDao.reserveAdminPageData(userId);
	}

}
