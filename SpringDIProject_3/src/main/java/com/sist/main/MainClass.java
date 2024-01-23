package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.spring.*;
public class MainClass {
	public static void main(String[] args) {
		String[] xml={"application-board.xml", "application-notice.xml"};
		ApplicationContext app=new ClassPathXmlApplicationContext("application-*.xml");
		Board b=app.getBean("board", Board.class);
		System.out.println("번호:"+b.getNo());
		System.out.println("번호:"+b.getName());
		System.out.println("번호:"+b.getSubject());
		
	}
}
