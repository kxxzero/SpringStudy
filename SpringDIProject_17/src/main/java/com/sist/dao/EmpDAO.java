package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.main.*;
import com.sist.mapper.*;

@Repository("eDao")
public class EmpDAO {
	@Autowired
	private EmpMapper mapper;
	
	public List<EmpVO> empAllData(){
		return mapper.empAllData();
	}

	public EmpVO empDatailData(int empno) {
		return mapper.empDatailData(empno);
	}
	
}
