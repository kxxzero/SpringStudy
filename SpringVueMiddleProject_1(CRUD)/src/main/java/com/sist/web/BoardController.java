package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 *  화면 변경
 *  - forward(request 전송)
 *  	└ 경로명/파일명(예: board/list) 형태
 *  - sendRedirect(재호출) 
 *  	└ redirect.do => request를 초기화
 *  		└ 일반 문자열, json 전송 시 사용
 */
import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class BoardController {
	
	// board/list를 찾아서 "board/list" 실행
	@GetMapping("board/list.do") 
	public String board_list() {
		return "board/list";
	}
	
	@GetMapping("board/insert.do")
	public String board_insert() {
		return "board/insert";
	}
	
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		model.addAttribute("no", no);
		return "board/detail";
	}
	
	@GetMapping("board/update.do")
	public String board_update(int no, Model model) {
		model.addAttribute("no", no);
		return "board/update";
	}
	
}
