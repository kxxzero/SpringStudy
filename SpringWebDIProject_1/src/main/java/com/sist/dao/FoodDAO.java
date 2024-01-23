package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
/*
 * 	Annotation(어노테이션) : 구분자
 *		1. 메모리 할당 요청(선택적 어노테이션) => 클래스별 구분 => 스테레오 타입
 *			= @Component
 *				- @Repository : DAO와 관련
 *				- @Service : BI(DAO 여러 개를 묶음)
 *				- @Controller : Model
 *				- @RestController : 브라우저 언어 통합 => Vue/React
 *				- @ControllerAdvice
 *				- @RestControllerAdvice
 *		2. DI(값을 부여)
 *			= @Autowired : 자동 부여
 *			= @inject
 *		3. AOP : 공통 모듈
 *			= @Aspect
 *				- @Before
 *				- @After
 * 
 */
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodsListData(int start, int end) {
		return mapper.foodListData(start, end);
	}
	
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
}
