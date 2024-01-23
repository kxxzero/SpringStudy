package com.sist.main;
/*
 *	1. XML 버전 => XML 파일 공유
 *	2. Annotation => 개별적으로 사용 가능(분업화가 가능하여 가장 많이 사용)
 *		= 사용자 정의 클래스
 *		= 라이브러리 클래스(어노테이션이 없음) => 상속으로 문제 해결
 *	3. 사용자 클래스는 어노테이션 이용
 *		= 라이브러리 클래스 XML => 공통 사용
 *	--------------------------------------------------
 *		4버전 형식 => 5버전 설정 파일(자바) => 6버전 분산 처리(MSA - Spring Cloud)
 *					==================================> 보안(모든 파일 => 자바)
 *	
 *	<Spring>
 *		= 프로그램 형식을 정리 => 모든 개발자가 동일한 포맷
 *			=> 형식 통일
 *		1) DI : 객제 생성
 *				객체 생성 시 필요한 데이터 주입
 *					=> 멤버 변수 초기화
 *						- setXxx()
 *						- 생성자
 *				개발자가 사용
 *				객체 소멸
 *				=> 컨테이너가 하는 역할
 *		2) 중복 코딩 : Spring에서는 자동 호출 가능(기존의 OOP(객체지향프로그램)에서는 자동 호출 불가 => 메소드 여러 개를 클래스로 제어)
 *				AOP => 단어
 *				Before / After / After-Returning / After-Throwing
 *				위빙, JoinPointer, PointCut
 *
 *				public String display() {
 *					try {
 *						Before => aaa()
 *					} catch(Exception ex) {
 *						@ After-Throwing => ccc()
 *					} finally {
 *						@ After => bbb()
 *					}
 *					@After-Returning
 *					return "";
 *					
 *		--------------------------------------------------
 *`		Application
 *
 *		Web Application(MVC)
 *					 
 */

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app2.xml");
		EmpDAO dao=(EmpDAO)app.getBean("empDAO"); // 클래스명이 ID 역할을 함 => 맨 앞글자만 소문자로 변경해서 입력, DI : 아이디나 키를 주고 주소값을 얻음
		List<EmpVO> list=dao.empDeptJoinData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
							+vo.getEname()+" "
							+vo.getDbday()+" "
							+vo.getSal()+" "
							+vo.getDvo().getDname()+" "
							+vo.getDvo().getDeptno());
		}
	}
}
