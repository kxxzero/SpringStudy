package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import com.sist.dao.*;
@Controller
public class FoodController {
	@Autowired // getBean() 대신 사용하는 이유 : application-context를 필요로 하는 번거로움 발생하기 때문
	private FoodDAO dao;
	
	@GetMapping("food/list.do")
	public String food_list() {
		return "food/list";
	}
	
	@GetMapping(value="food/list_vue.do", produces="text/plain;charset=UTF-8")
	@ResponseBody // 화면이 아닌 필요한 데이터가 넘어감 => 더 발전된 버전 : @RestContoller(최신 사용 권장)
	public String food_list_vue(int page) {
		// [{fno:1, poster:'', name:'', ...}]
		/*
		 * VueJS 연결 전에 초기값을 설정
		 * 	data() {
		 * 		return {
		 * 			page:1
		 * 		}
		 * 	}
		 */
		
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		// "WHERE num BETWEEN #{start} AND #{end}"
		// 						map.get("start") map.get("end")
		List<FoodVO> list=dao.foodListData(map);
		int totalpage=dao.foodTotalPage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;	
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		int i=0;
		JSONArray arr=new JSONArray(); // [] : Array / {} : VO
		for(FoodVO vo:list) {
			JSONObject obj=new JSONObject(); // VO를 담는 객체
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster());
			// [{fno:1, name:'', poster:'',  ...}]
			if(i==0) { // 맨 처음에만 적용
				obj.put("curpage", page);
				obj.put("totalpage", totalpage);
				obj.put("startPage", startPage);
				obj.put("endPage", endPage);
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	
	// 별도로 페이지를 나누는 코드 작성 필요
	@GetMapping("food/page_vue.do")
	@ResponseBody
	public String food_page_vue(int page) {
		// startPage, endPage, curpage, totalpage
		return "";
	}
}
