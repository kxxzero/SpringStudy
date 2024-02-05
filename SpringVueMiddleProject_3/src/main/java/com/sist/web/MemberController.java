package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

public class MemberController {
	@GetMapping("member/logout.do")
	public String member_logout(HttpSession session) {
		session.invalidate();
		return "redirect:";
	}
}
