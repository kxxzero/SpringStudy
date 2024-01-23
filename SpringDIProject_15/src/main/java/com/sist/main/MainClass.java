package com.sist.main;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.config.DataBaseConfig;
import com.sist.dao.*;
import com.sist.mapper.*;
public class MainClass {
	public static void main(String[] args) {
//		ApplicationContext app=new ClassPathXmlApplicationContext("application-datasource.xml");
		Class[] cls= {CustomerConfig.class, DataBaseConfig.class};
		AnnotationConfigApplicationContext
		FoodDAO dao=(FoodDAO)app.getBean("FDao");
		Scanner scan=new Scanner(System.in);
		while(true) {
			System.out.println("===== Menu =====");
			System.out.println("1. 이름");
			System.out.println("2. 주소");
			System.out.println("3. 음식종류");
			System.out.println("4. 이름+주소");
			System.out.println("5. 이름+음식종류");
			System.out.println("6. 주소+음식종류");
			System.out.println("7. 이름+주소+음식종류");
			System.out.println("================");
			System.out.println("메뉴 선택:");
			int no=scan.nextInt();
			String data="";
			switch(no) {
			case 1: data="N";
				break;
			case 2: data="A";
				break;
			case 3: data="T";
				break;
			case 4: data="NA";
				break;
			case 5: data="NT";
				break;
			case 6: data="AT";
				break;
			case 7: data="NAT";
				break;
			default:
				System.out.println("프로그램 종료");
				System.exit(0);
			}
			String[] fsArr=data.split("");
			Map map=new HashMap();
			System.out.println("검색어 입력:");
			String ss=scan.next();
			map.put("fsArr", fsArr);
			map.put("ss", ss); // 검색어
			List<FoodVO> listdao.foodFindData(map);
			for(FoodVO vo:list) {
				System.out.println(vo.getName()+" "
						+vo.getAddress()+" "
						+vo.getType());
			}
			
		}
	}
}
