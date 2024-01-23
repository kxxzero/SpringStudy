package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.*;
public interface EmpMapper {
	
	/*
	 * 	<resultMap>
	 * 		<result property="dvo.dname" column="dname"/>
	 * 	</resultMap>
	 * 
	 * 		dvo.setDname(rs.getString("dname"))
	 */
	
	@Results({ // ResultMap이 아닌 Result 사용
		@Result(property="dvo.dname", column="dname"), // dvo(DeptVO)에서 setDname() 메소드를 찾음
		@Result(property="dvo.loc", column="loc")
	})
	@Select("SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal, dname, loc "
			+ "FROM emp JOIN dept "
			+ "ON emp.deptno=dept.deptno")
	public List<EmpVO> empDeptJoinData(); // 제네릭스 => EmpVO 안에 DeptVO도 포함하고 있음
}
