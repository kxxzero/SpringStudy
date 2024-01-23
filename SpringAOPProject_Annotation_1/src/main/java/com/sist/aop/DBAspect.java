package com.sist.aop;

import java.util.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.sist.dao.*;

public class DBAspect {
	@Autowired
	private EmpDAO dao;
	
	// 1. before
	@Before("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void before() {
		dao.getConnection();
		System.out.println("오라클 연결 자동 호출:CallBack");
	}
	
	// 2. after
	@After("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void after() {
		dao.disConnection();
		System.out.println("오라클 해제 자동 호출:CallBack");
	}
	
	// 3. around
	@Around("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		System.out.println();
		Object obj=null;
		long start=System.currentTimeMillis();
		System.out.println("호출된 메소드:"+jp.getSignature().getName());
		obj=jp.proceed();
		long end=System.currentTimeMillis();
		System.out.println("수행 시간:"+(end-start));
		
		return obj;
	}
	
	// @After-
	
	
}
