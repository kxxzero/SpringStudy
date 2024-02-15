package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.FoodDAO;
import com.sist.dao.NoticeDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.NoticeVO;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodDAO dao;
	
	@Autowired
	private NoticeDAO nDao;
	
	@Override
	public List<FoodVO> foodFindData(Map map) {
		// TODO Auto-generated method stub
		return dao.foodFindData(map);
	}

	@Override
	public int foodFindCount(Map map) {
		// TODO Auto-generated method stub
		return dao.foodFindCount(map);
	}

	@Override
	public FoodVO foodDetailData(int fno) {
		// TODO Auto-generated method stub
		return dao.FoodDetailData(fno);
	}

	@Override
	public List<FoodVO> foodListData(Map map) {
		// TODO Auto-generated method stub
		return dao.foodListData(map);
	}

	@Override
	public int foodListCount() {
		// TODO Auto-generated method stub
		return dao.foodListCount();
	}

	@Override
	public FoodVO foodListDetailData(int fno) {
		// TODO Auto-generated method stub
		return dao.foodListDetailData(fno);
	}
	
	@Override
	public List<NoticeVO> noticeTop7() {
		// TODO Auto-generated method stub
		return nDao.noticeTop7();
	}

	@Override
	public List<FoodVO> foodTop7() {
		// TODO Auto-generated method stub
		return dao.foodTop7();
	}

	@Override
	public List<FoodVO> foodHome12() {
		// TODO Auto-generated method stub
		return dao.foodHome12();
	}
}
