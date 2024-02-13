package com.sist.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("freeboard/")
public class BoardRestController {
	@Autowired
	private FreeBoardService service;
	
	
	@GetMapping(value="list_vue.do", produces="text/plain;charset=UTF-8")
	public String freeboard_list(int page) throws Exception {
		
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		List<FreeBoardVO> list=service.freeboardListData(start, end);

		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
	}
	
	@GetMapping(value="page_vue.do", produces="text/plain;charset=UTF-8")
	public String freeboard_page(int page) throws Exception {
		
		int totalpage=service.freeboardTotalpage();
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	
	@PostMapping(value="insert_vue.do", produces="text/plain;charset=UTF-8")
	public String freeboard_insert(FreeBoardVO vo) {
		String result="";
		try {
			service.freeboardInsert(vo);
			result="yes";
		}catch(Exception ex) {
			result=ex.
		}
	}
}
