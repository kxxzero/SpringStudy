package com.sist.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.sist.vo.*;

// BI => DAO 또는 Manager를 여러 개 통합해서 사용 => 결합성이 낮은 프로그램 => 스프링에서 권장
@Service
public interface FoodService {
	public List<FoodVO> foodListData(int start, int end);
	   
	public int foodTotalPage();
	   
	public FoodVO foodDetailData(int no);
	
	public MemberVO isLogin(String id, String pwd);
}
