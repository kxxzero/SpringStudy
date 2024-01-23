package com.sist.main;

import java.util.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sist.dao.*;
import com.sist.config.*;
public class MainClass2 {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(EmpConfig.class) // 컴파일된 파일(.class)을 넘겨줌
		EmpDAO dao=(EmpDAO)app.getBean("dao"); // id명이 부여될 경우에는 id 입력 / 아닐 시에는 default 값을 입력
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
							+ vo.getEname()+" "
							+ vo.getJop());
		}
	}
}
