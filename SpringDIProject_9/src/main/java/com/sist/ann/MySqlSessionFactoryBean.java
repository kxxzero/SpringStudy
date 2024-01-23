package com.sist.ann;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * 	<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
	/>
 */

@Component("ssf")
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean{

	/* @Override - 삭제 */
	@Autowired // 메모리 할당 후 '@Autowired'를 통해 자동으로 값을 채움 => getBean과 같은 역할
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		super.setDataSource(dataSource);
	}
	
}
