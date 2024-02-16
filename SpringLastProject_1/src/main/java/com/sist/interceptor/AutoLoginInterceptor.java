package com.sist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * 	xml<mvc:interceptor>
 * 
 * 	main.do ==========> DispatcherServlet ==========> HandlerMapping
 * 														url 주소를 이용해서 해당 메소드 찾기
 * 														@GetMapping("main/main.do")
 * 														public String main_main() {
 * 															return "" 
 * 																- postHandle() // 데이터를 더 추가해서 전송 가능(예: 쿠키)
 * 															=> Vue Resolver
 * 														}
 * 															↓ => Model
 * 																- afterCompletion // 권한 관련
 * 															JSP
 */

public class AutoLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}
