package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	// list.do
	@RequestMapping("board/list.do")
	public String board_list(String page, Model model) {
		// 사용자가 전송한 모든 데이터는 매개변수로 받음
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (curpage*10)-9);
		map.put("end", curpage*10);
		List<BoardVO> list=dao.boardListData(map);
		int totalpage=dao.boardTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
				
		//  Model => 전송 객체
		return "board/list"; // /board/list.jsp
	}
	
	// insert.do
	@RequestMapping("board/insert.do")
	public String board_insert() {
		return "board/insert";
	}
	
	// insert_ok.do => command 객체
	@RequestMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:list.do"; 
	}
	
	// detail.do?no=${vo.no}
	@RequestMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	
	@RequestMapping("board/update.do")
	public String board_update(int no, Model model) { // model은 데이터를 넘겨줄 때만 사용
		BoardVO vo=dao.boardUpdateData(no);
		model.addAttribute("vo", vo);
		return "board/update";
	}
}
