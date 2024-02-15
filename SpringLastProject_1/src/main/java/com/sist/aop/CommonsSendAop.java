package com.sist.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.manager.NewsManager;
import com.sist.service.FoodService;
import com.sist.vo.FoodVO;
import com.sist.vo.NewsVO;
import com.sist.vo.NoticeVO;

@Aspect
@Component
public class CommonsSendAop {
	@Autowired
	private FoodService service;
	
	@Autowired
	private NewsManager mgr;
	
	// 모든* 컨트롤러 안의 모든* 메소드가 호출 될 때 / 무조건 실행=> After
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void footerSend() {
		List<FoodVO> fList=service.foodTop7();
		List<NoticeVO> nList=service.noticeTop7();
		List<NewsVO> newList=mgr.newsFind("맛집");
		
		/*
		 * @Controller, @RestController를 제외한 모든 클래스에서 request가 필요한 경우에 사용
		 * ==============================
		 * 	└ request를 요청하면 DispatcherServlet에 의해 매개변수로 주입
		 */
		
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		request.setAttribute("nList", nList);
		request.setAttribute("fList", fList); // fList => footer
		request.setAttribute("newList", newList);
	}
}
