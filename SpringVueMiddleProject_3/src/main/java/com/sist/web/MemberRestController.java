package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.vo.MemberVO;
import com.sist.service.*;
@RestController
public class MemberRestController {
	@Autowired
	private FoodService service;
	
	@GetMapping(value="member/login_vue.do", produces="text/plain;charset=UTF-8")
	public String member_login(String id, String pwd, HttpSession session) {
		MemberVO vo=service.isLogin(id, pwd);
		
		if(vo.getMsg().equals("OK")) {
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
		}
		return vo.getMsg();
	}
}
