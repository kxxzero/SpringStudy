package com.sist.spring;
/*
 * Spring : 오픈 라이브러리, 클래스 관리자(프로그램에 맞게 맞든 클래스를 관리) => 사용자 정의 클래스(어노테이션) / 라이브러리 클래스(공통 => XML)
 * 	1) 일반 라이브러리보다 확장성이 좋음(변경해서 사용 가능) => 프레임워크(전자정부 프레임워크 / ANY 프레임워크)	
 * 								
 * 	2) 지원하는 라이브러리
 * 		= Core : 객체 생성부터 객체 소멸까지
 * 			- <bean> : 클래스 1개
 * 			- <context:component-scan> : 패키지 단위(*) => 선택적(어노테이션)
 * 			- @Bean : 순수하게 자바만 이용
 * 		= Data Access
 * 			- JDBC : 
 * 			- ORM : MyBatis, JPA
 * 			- JMS : 
 * 			- OXM : XML 연동
 * 		= Web : Web / MVC
 * 		= AOP : 공통 모듈 => Commons ~
 * 			- Transaction
 * 			- Security
 * 
 * 	3) 컨테이너 : 객체를 저장하는 공간 => 객체의 생명주기를 담당(생성 ~ 소멸)
 *	 	= 스프링에서 지원하는 컨테이너
 *			- BeanFactory
 *			- ApplicaionContext
 *				· GenericXmlApplicationContext => close() : Application
 *				· AnnotationConfigApplicationContext : XML 없이 사용
 *				· WebApplication
 *			
 *	4) 스프링은 클래스가 많은 경우나 연결 관계가 많은 경우에 주로 사용 => 자바 전용이 아님
 *
 *	5) 스프링 프레임워크 특징
 *		= 경량 컨테이너 : 스프링에서 객체 생성부터 소멸까지 담당
 *			- DL : 클래스 찾기 => getBean()
 *			- DI : 객체 생성 시 멤버 변수의 초기값이 필요한 경우 => setterDI / 생성자 DI
 *					=> 자동 로그인, 데이터베이스 처리
 *		= POJO(Plain Old Java Object) 방식을 사용 => 특정 프레임워크 기술에 의존하지 않음
 *			- Plain : 상속이나 인터페이스가 없는 클래스 => 결합성이 낮음
 *			- Old Java : 일반 자바
 *		= 클래스가 독립적으로 저장 => 유지보수하기 좋음
 *		= 확장성
 *		= 필요한 모든 라이브러리를 지원
 *
 *	--------------------------------------------------
 *	1. ***DI : 스프링을 통해서 멤버 변수의 초기화
 *			   객체 생성
 *		=> 라이브러리는 자바 소스를 추가 불가
 *		=> 라이브러리에서 읽어가는 파일 생성
 *			=> XML : 태그명 / 속성명이 다르면 인식을 못함
 *			=> Annotation이 있는 클래스 : 스프림에서 지원하는 어노테이션만 이용
 *						
 *	2. DL
 *	3. ***AOP
 *	4. DataBase(ORM)
 *	5. ***Transaction
 *	6. ***Web(MVC)
 *	7. Security	
 *	--------------------------------------------------
 *	
 *			
 */

// 스프링에서 관리하는 클래스(VO 제외)
public class Sawon {
	// 1. setter DI
	private int sabun;
	private String name;
	private String sex;
	
	public int getSabun() {
		return sabun;
	}
	public void setSabun(int sabun) {
		this.sabun = sabun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
