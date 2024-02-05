package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 *	화면 변경 / 다운로드
 *		String / void
 * 		=> 	forward / sendRedirect('_ok'와 같은 기존의 화면을 이용할 때 주로 사용)  => return "redirect:...do"
 * 			return "경로/파일명" => request에 값을 추가해서 전송
 */

@Controller
public class FoodController {
	@GetMapping("food/list.do")
	public String food_list(Model model) {
		model.addAttribute("login_jsp", "../member/login.jsp");
		return "food/list";
	}
	
	@GetMapping("food/detail.do")
	public String food_detail(int fno, Model model) {
		model.addAttribute("login_jsp", "../member/login.jsp");
		model.addAttribute("fno", fno);
		return "food/detail";
	}

}
