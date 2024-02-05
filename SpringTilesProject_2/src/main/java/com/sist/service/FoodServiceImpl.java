package com.sist.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.*;
import com.sist.dao.*;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodDAO dao;
	
	@Autowired
	private MemberDAO mDao;
	
	@Autowired
	private ReplyDAO rDao;
	
	
	// Food
	public List<FoodVO> foodListData(int start, int end) {
		return dao.foodListData(start, end);
	}
	
	public int foodTotalPage() {
		return dao.foodTotalPage();
	}
	
	@Override
	public FoodVO foodDetailData(int fno) {
		return dao.foodDetailData(fno);
	}
	
	
	// Reply
	@Override
	public List<ReplyVO> replyListData(int fno) {
		return rDao.replyListData(fno);
	}
	
	@Override
	public void replyInsert(ReplyVO vo) {
		rDao.replyInsert(vo);
	}
	
	@Override
	public void replyDelete(int rno) {
		rDao.replyDelete(rno);
	}
	
	
	// Member
	@Override
	public MemberVO isLogin(String id, String pwd) {
		return mDao.isLogin(id, pwd);
	}
}
