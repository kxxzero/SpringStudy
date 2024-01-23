package com.sist.proxy;
/*	
 * 	proxy : 대리자 => 대신 호출
 * 	
 * 	AOP
 * 	위빙 => 메소드를 결합
 * 	 
 * 	어떤 클래스의 어떤 메소드를 적용할 것인지 지정 => PointCut
 * 	어떤 시점 => JoinPoint
 * 		= Before : try 수행 전
 * 		= After : finally에서 수행
 * 		= After-Returning : 정상 수행 => 웹 전송
 * 		= After-Throwing : catch 수행 => 웹 오류 발생
 * 		= Around : 로그 / 트랜잭션
 * 			- 로그
 * 				시간, 호출 메소드 => setAutoCommit(false)
 * 					// 수행 문장
 * 				시간 => Commit()
 * 	PointCut + JointPoint = Advice
 * 	
 */

public class Proxy {
	private Sawon sawon;
	public Proxy(Sawon sa) {
		this.sawon=sa;
	}
	
	// 위빙
	public void display() {
		System.out.println("Before 처리");
		sawon.disPlay();
		System.out.println("After 처리");
	}
}
