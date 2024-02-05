package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {
	@GetMapping() // 아무것도 보내지 않을 때는 GetMapping 사용
	public String food_list() {
		return "food/list"; //forward, sendRedirect => 파일명만 전송 가능(일반 데이터는 전송 불가)
	}
}
