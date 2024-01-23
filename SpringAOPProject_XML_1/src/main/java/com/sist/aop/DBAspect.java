package com.sist.aop;

import java.util.*;

import org.aspectj.lang.ProceedingJoinPoint;

import com.sist.dao.*;
/*
 * 	DI
 * 		- Injection => setter, 생성자
 * 		- 클래스와 클래스의 연결 관계 설정
 * 
 * 	Advice
 * 		- pointCut : 어떤 메소드에 적용
 * 			· execution("* 패키지명.클래스명.*()"
 * 
 * 			· within("패키지명.*")
 * 				
 * 		- JoinPoint : 어떤 위치
 * 			· Before => 메소드 시작 전
 * 			· After => finally
 * 			· After-Throwing => catch(에러 발생)
 * 			· After-Returning => Return(정상 수행)
 * 			· Around => 핵심 코드의 전/후
 * 	=> 통합해서 새로운 기능을 생성 : 위빙(Weaving) => Proxy(대리자) 패턴
 */
public class DBAspect {
	private EmpDAO dao;
	
	public void setDao(EmpDAO dao) {
		this.dao=dao;
	}
	
	// try 집입 전
	public void before() {
		dao.getConnection();
	}
	
	// finally
	public void after() {
		dao.disConnection();
	}
	
	// 데이터 출력 => after-returning
	public void afterReturning(Object obj) {
		System.out.println("===== 결과값 자동 처리 =====");
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list) {
		System.out.println(vo.getEmpno()+" "
						+ vo.getEname()+" "
						+ vo.getJob()+" "
						+ vo.getDbday()+" "
						+ vo.getSal());
		}
	}
	
	// 에러 => after-throwing
	public void afterThrowing(Throwable ex) {
		System.out.println("===== 에러 발생 =====");
		ex.printStackTrace();
		// Web => @ControllerAdvice : 공통 예외 처리
	}
	
	// 시간 => around
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		Object obj=null;
		long start=System.currentTimeMillis();
		System.out.println("호출된 메소드:"+jp.getSignature().getName());
		// 사용자가 호출한 메소드
		// 메소드 호출
		obj=jp.proceed(); // dao.empListData() => invoke()
		long end=System.currentTimeMillis();
		System.out.println("수행 시간:"+(end-start));
		
		return obj;
	}
}
