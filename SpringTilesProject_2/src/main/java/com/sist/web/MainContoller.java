package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContoller {
	@GetMapping("main/main.do")
	public String main_main() {
		return "main";
	}
}
