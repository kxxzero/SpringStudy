package com.sist.main;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

/*
 * 	OOP(객체지향프로그램)에서 반복 소스가 있는 경우
 * 		= 1개의 클래스 : 메소드
 * 		= 여러 개의 클래스 안 : 클래스
 * 	=> 자동 호출(콜백 함수)이 없음 => AOP 등장
 * 
 * 	*** AOP는 OOP를 보완한 패턴
 */

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		MyDAO dao=(MyDAO)app.getBean("dao"); // app.xml에서 등록한 'dao'라는 id를 가진 객체를 가지고 옴
		
		System.out.println("===== SELECT =====");
		dao.select();
		System.out.println("===== INSERT =====");
		dao.insert();
		System.out.println("===== UPDATE =====");
		dao.update();
		System.out.println("===== DELETE =====");
		dao.delete();		
	}

}
