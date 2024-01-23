package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import com.sist.dao.*;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("application.xml");
		ShopDAO dao=(ShopDAO)app.getBean("dao");
		List<ShopVO> list=dao.shopListData();
		for(ShopVO vo:list) {
			System.out.println(vo.getNo()+" "
							+vo.getTitle());
		}
		
		Scanner scan=new Scanner(System.in);
		System.out.println("번호 입력:");
		int no=scan.nextInt();
		System.out.println("===== 상세 보기 =====");
		ShopVO vo=dao.ShopDetailData(no);
		System.out.println("번호:"+vo.getNo());
		System.out.println("이름:"+vo.getTitle());
		System.out.println("소개:"+vo.getMsg());
		System.out.println("주소:"+vo.getAddress());
		
		System.out.println("===== 검색 =====");
		System.out.println("검색어 입력:");
		String title=scan.next();
		List<ShopVO> fList=dao.shopFindData(title);
		for(ShopVO fvo:fList) {
			System.out.println(fvo.getNo()+" "
							+fvo.getTitle());
		}
	}

}
