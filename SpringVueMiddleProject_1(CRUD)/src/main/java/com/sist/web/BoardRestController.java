package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *  자바스크립트 연동을 위해 작성하는 공간
 *  	=> 데이터를 받아서 처리 후 결과값 전송
 *  		=> Rest(GET/POST/PUT/DELETE)
 */
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController // Rest를 관리하는 역할 => RestBody의 변경 버전
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	
	@GetMapping(value="board/list_vue.do", produces="text/plain;charset=UTF-8") // 데이터 전송 => value
	public String board_list(int page) throws Exception {
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		List<BoardVO> list=dao.boardListData(start, end);
		ObjectMapper mapper=new ObjectMapper(); // JSON(Jacksion : JSON 자동 생성)
		String json=mapper.writeValueAsString(list);
		
		return json;
	}
	
	@GetMapping(value="board/page_vue.do", produces="text/plain;charset=UTF-8") // 데이터 전송 => value
	public String board_page(int page) throws Exception {
		Map map=new HashMap();
		int totalpage=dao.boardTotalPage();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	
	
	@PostMapping(value="board/insert_ok.do", produces="text/plain;charset=UTF-8")
	public void board_insert_ok(BoardVO vo) { // vo로 받아서 모아서 처리
		dao.boardInsert(vo);
//		String url="<script>"
//				+ "location.href=\"list.do\""
//				+ "</script>";
//		return "url";
		// redirect, forward는 파일명이나 경로명을 전송할 수 없고 문자열이나 json 전송만 가능하기 때문에 <script> 처리가 필요함
	}
	
	
	// {" ":" ", " ":" "} JSONObject
	/*
	 * 	@RestController => json 형태
	 * 	class A{
	 * 		@GetMapping()
	 * 		public List<FoodVO> listdata() {
	 * 			List<FoodVO> list=dao.getList()
	 * 			return list;
	 * 		}
	 * 	}
	 */
	@GetMapping(value="board/detail_vue.do", produces="text/plain;charset=UTF-8")
	public String board_detail(int no) throws Exception {
		BoardVO vo=dao.boardDetailData(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		System.out.println(json);
		
		return json;
	}
	
	@GetMapping(value="board/update_vue.do", produces="text/plain;charset-UTF-8")
	public String board_update(int no) throws Exception {
		BoardVO vo=dao.boardUpdateData(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		
		return json;
	}
	
	@PostMapping(value="board/update_ok.do", produces="text/plain;charset-UTF-8")
	public String board_update_ok(BoardVO vo) {
		String result=dao.boardUpdate(vo);
		
		return result;
	}
	
	@GetMapping(value="board/delete_vue.do", produces="text/plain;charset-UTF-8")
	public String board_delete(int no, String pwd) {
		String result=dao.boardDelete(no, pwd);
		return result;
	}
}

