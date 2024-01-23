package com.sist.dao;

import java.util.List;
import org.apache.ibatis.annotations.Select;

public class ShopDAO {
	
	private ShopMapper mapper;
	
	public void setMapper(ShopMapper mapper) {
		this.mapper = mapper;
	}

	public List<ShopVO> shopListData() {
		return mapper.shopListData();
	}
	
	public ShopVO ShopDetailData(int no) {
		return mapper.ShopDetailData(no);
	}
	
	public List<ShopVO> shopFindData(String title) {
		return mapper.shopFindData(title);
	}
}
