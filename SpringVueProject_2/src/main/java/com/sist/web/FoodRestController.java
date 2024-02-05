package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;

// Vue로 데이터를 전송하는 역할 => [],  {}
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	// {키:값,키:값} => JSON
	@GetMapping(value="food/list_vue.do", produces="text/plain;charset=UTF-8")
	public String food_list_vue() throws Exception {
		List<FoodVO> list=dao.foodListData();
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value="food/detail_vue.do", produces="text/plain;charset=UTF-8")
	public String food_detail_vue(int fno) throws Exception {
		FoodVO vo=dao.foodDetailData(fno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
}
