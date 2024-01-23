package com.sist.main;

/*
 * DAO => 자동 부여
 * DAO => 직접
 */

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass {
	public static void main(String[] args) {
		// xml을 스프링 컨테이너에 전송
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("empDAO"); // id명이 부여될 경우에는 id 입력 / 아닐 시에는 default 값을 입력
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
							+ vo.getEname()+" "
							+ vo.getJop());
		}
	}
}
