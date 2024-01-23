package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;

/*
 * @Component
 * 	- 사용 위치 : TYPE => 클래스에만 적용 가능
 * 
 * @Autowired
 * 	- 사용 위치 : CONSTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTATION_TYPE => 클래스에는 적용 불가
 */

@Component
public class MainClass {
	// @Autowired + @Qualifier = @Resource => 1.8버전까지만 지원
	@Autowired // 객체 선택 불가
	@Qualifier("memberDAO") // @Qualifier : 특정 객체를 선택
	private OracleDB ob;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		mc.ob.display();
	}

}
