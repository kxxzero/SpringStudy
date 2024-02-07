package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*	JSP와 연결 => 화면 변경 => forward(request) "경로명/파일명"/ redirect(request를 초기화)
 *	"redirect:... .do" => 글쓰기 => 목록
 *	Vue / React => router , include
 */ 
@Controller
public class FoodController {
	@GetMapping("food/list.do")
	public String food_list() {
		return "food/list";
	}
}
