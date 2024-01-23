package com.sist.spring2;

/*
 * 인터페이스 이용 시 결합성이 new보다는 낮아짐
 * 	=> 인터페이스 수정 시 관계 있는 모든 클래스에 에러가 발생하는 단점이 있음
 * 	=> 소프트웨어 => 인터페이스는 고정 => default
 */

// MainClass : 클라이언트 역할
public class MainClass {

	public static void main(String[] args) {
		// 불러들이는 역할
		Hello hello=new HelloImpl();
		hello.sayHello("심청이");
	}
	
}
