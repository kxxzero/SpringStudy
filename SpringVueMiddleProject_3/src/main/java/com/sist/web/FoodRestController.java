package com.sist.web;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 다른 시스템이나 언어(자바스크립트 - Ajax, Vue, React 등)와 연동할 때 사용 => JSON/일반문자열 전송
// 직접 JSON 생성 : simple-json => 자동 완성(jacksion)
// jacksion => JSON(자바스크립트 객체 표현법) {"키":값, "키":값,...} => 키를 멤버변수로 인식하기 때문에 변수명과 동일하게 입력해야 함

// 오라클 : ROW, 자바 : 객체, 자바스크립트 :{}

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.vo.*;
import com.sist.service.*;

@RestController // 기존 : RestControllerBody() - 메소드형 → 최신 버전 : RestController{} - 클래스형
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@GetMapping(value="food/list_vue.do", produces="text/plain;charset=UTF-8")
	public String food_list(int page) throws Exception {
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		List<FoodVO> list=service.foodListData(start, end);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list); // [{}, {}, {}, ...]
		
		return json;
	}
	
	@GetMapping(value="food/page_vue.do", produces="text/plain;charset=UTF-8")
	public String food_page(int page) throws Exception {
		int totalpage=service.foodTotalPage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		
		Map map=new HashMap();
		map.put("curpage", page); // ("키", 값)
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		// response.data={curpage:1, totalpage:10, startPage:1, endPage:10}
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	
	// 상세보기
	@GetMapping(value="food/detail_vue.do", produces="text/plain;charset=UTF-8")
	public String food_detail(int fno, HttpSession session) throws Exception {
		String id=(String)session.getAttribute("id");
		FoodVO vo=service.foodDetailData(fno);
		String sId="";
		if(id==null) {
			sId="";
		} else {
			sId=id;
		}
		vo.setSessionId(sId); // 임시 저장 => 댓글 사용 => 수정 / 삭제
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		
		return json;
	}
	
}
