package com.sist.main;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

import lombok.Data;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		SeoulDAO sDao=(SeoulDAO)app.getBean("sDao");
		GoodsDAO gDao=app.getBean("gDao", GoodsDAO.class);
		String[] stable= {"", "seoul_location"};
		String[] gtable= {"", "goods_all", "goods_best", "goods_sepcial", "goods_new"};
		Scanner scan=new Scanner(System.in);
		
		while() {
			System.out.println("===== Menu =====");
			System.out.println("1. 여행");
			System.out.println("2. 상품");
			System.out.println("3. 종료");
			System.out.println("====================");
			System.out.println("선택:");
			int index=scan.nextInt();
			if(index==1) {
				System.out.println("===== 서브 메뉴 =====");
				System.out.println("1. 명소");
				System.out.println("2. 자연 & 관광");
				System.out.println("3. 쇼핑");
				System.out.println("====================");
				System.out.println("여행 선택");
				int sno=scan.nextInt();
				String tab_name=stable[sno];
				Map map=new HashMap();
				map.put("table_name", tab_name);
				List<SeoulVO> list=sDao.seoulListData(tab_name);
				for(SeoulVO vo:list) {
					System.out.println(vo.getNo()+" "
									+ vo.getTitle());
				}
				System.out.println("====================");
				System.out.println("상세하게 볼 여행 번호 선택:");
				int no=scan.nextInt();
				map.put("no", no);
				SeoulVO vo=sDao.seoulDetailData(map);
				System.out.println("여행지:"+vo.getTitle());
				System.out.println("소개:"+vo.getMsg());
				System.out.println("주소:"+vo.getAddress());
			} else if(index==2) {
				System.out.println("===== 서브 메뉴 =====");
				System.out.println("1. 전체 상품");
				System.out.println("2. 베스트 상품");
				System.out.println("3. 특가 상품");
				System.out.println("4. 신상품");
				System.out.println("====================");
				System.out.println("서브 메뉴 선택");
				int gno=scan.nextInt();
				String table_name=gtable[gno];
				Map map=new HashMap();
				map.put("table_name", table_name);
				List<GoodsVO> list=gDao.goodsListData(map);
				for(GoodsVO vo:list) {
					System.out.println(vo.getNo()+"."
									+ vo.getName());
				}
				System.out.println("====================");
				System.out.println("상세하게 볼 상품 선택:");
				int no=scan.nextInt();
				map.put("no", no);
				GoodsVO vo=gDao.goodsDetailData(map);
				System.out.println("상품명:"+vo.getName());
				System.out.println("가격:"+vo.getPrice());
				System.out.println("소개:"+vo.getSub());
				System.out.println("할인:"+vo.getDiscount()+"%");
//				System.out.println("첫 구매 가격:"+vo.getFirst_price());
			} else {
				
			}
		}
	}

}
