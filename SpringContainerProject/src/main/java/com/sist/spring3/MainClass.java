package com.sist.spring3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Spring : 클래스 관리자
 * 			멤버변수 초기화 / 메소드 호출 / 생성자 매개변수 => DI(데이터 값을 부여 => XML 사용)
 *  1.  프로젝트에 필요한 클래스를 모아서 관리 => Container(클래스 관리자 역할)
 *  	=> 경량 컨테이너
 *  	=> 단순한 연결 관계(하나의 연결로 제어)
 *  	=> 라이브러리로 제공
 *  		- 오픈 소스
 *  		- Core(클래스틑 찾아주는 역할)
 *  		- Container 클래스
 *  			BeanFactory : Core(DI+DL)만 제어
 *  				|
 *  			ApplicationContext : 상속을 받은 후 Core에 AOP(자동 호출)
 *  				|
 *  			WebApplicationContext : Core / AOP / MVC
 *  	
 *  	컨테이너 : 컴포넌트 여러 개를 묶어서 관리 => Spring의 역할
 *  		=> 객체의 생명 주기
 *  			= 객체 생성(데이터 베이스에 정보를 넘겨주어야 함)
 *  				*** new를 사용해야 하는 유일한 클래스 : VO(사용자 정의 데이터형이기 때문에 필요할 때마다 가져다 사용해야 함)
 *  			= 객체 변수의 초기화(DI : 데이터 값을 대입해줌)
 *  			= 객체 소멸(필요 없어지는 순간 소멸)
 *  		-------------------------
 *  		=> 객체를 찾아주는 역할(DL : 객체 이름으로 찾아줌)
 *  		=> Core = DI + DL
 *  
 *  	컴포넌트 : 개발자가 생성한 클래스 1개(기능을 수행하는 객체 단위)
 *  
 *  	1) 클래스 관리
 *  		= 클래스 등록 => 찾기 : DL
 *  	2) 객체 생성 시 필요한 변수가 주입 :  DI
 *  		
 */

// 클래스 찾기
public class MainClass {
	public static void main(String[] args) {
		/*
		 * Spring에서 사용하는 default 폴더(경로명 없이 자동 인식 => classpath)
		 *  => classpath(src/main/java)
		 *  
		 *  1. XML 파싱
		 *  2. 컨테이너에 XML 전송 
		 */
		
		ApplicationContext app=new ClassPathXmlApplicationContext("application.xml"); // ApplicationContext : 클래스를 관리하는 클래스 / ClassPathXmlApplicationContext : 파싱
		// => 등록된 클래스를 모아서 관리해주는 역할 => 컨테이너
		
		// 객체 메모리 할당 => 등록
		/*
		 * class ApplicationContext {
		 * 		Map map=new HashMap();
		 * 		map.put("id명", Class.forName("class명")); // 값 부여하기
		 * 
		 * 		public Object getBean(String key) {
		 * 			return map(key);
		 * 		}
		 * }
		 */
		
		// 객체 찾기 => DL
		Board board=(Board)app.getBean("board"); // Object -> Board로 형변환
		System.out.println("board="+board);
		board.board_list();
		
		Board board1=(Board)app.getBean("board");
		System.out.println("board1="+board1);
		board1.board_list();
		
		Board board2=(Board)app.getBean("board");
		System.out.println("board2="+board2);
		board2.board_list();
		
		
//		Board notice=(Notice)app.getBean("notice");
//		notice.notice_list();
//		Board member=(Member)app.getBean("member");
//		member.member_list();
	}
}
