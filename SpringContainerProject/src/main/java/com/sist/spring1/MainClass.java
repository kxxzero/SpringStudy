package com.sist.spring1;

/*
 * MainClass는 Hello 클래스에 의존함
 * 	=> 결합성이 강한 프로그램 => 다른 개발자에게 영향을 미치기 쉬움
 * 	=> 유지보수가 어렵다는 단점이 있음
 * 	=> 상호 연결이 어려움(연결은 쉬우나 중단 시 문제 발생)
 * 	=> 가급적이면 new(객체 생성)를 사용하지 않을 것을 권장
 * 	*** 해결 방법 : 결합성이 낮은 프로그램을 제작해야 함 => new 대신 Interface 이용 => 본인이 작업한 파일에만 영향을 미침
 * 		=> Spring은 Interface 기반 (클라이언트와 서버 사이에서 인터페이스가 중간 다리 역할을 해줌)
 */

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hello hello=new Hello();
		hello.sayHello("홍길동");
	}

}
