package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class EmpDAO {
	@Autowired
	private EmpMapper mapper;

	// 값을 받아옴
//	public void setMapper(EmpMapper mapper) {
//		this.mapper = mapper;
//	}
	
	public List<EmpVO> empDeptJoinData() {
		return mapper.empDeptJoinData();
	}
}
