package com.sist.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.*;

import com.sist.mapper.*;

@Configuration
// <context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages="com.sis.*")
public class EmpConfig {
	/*
	 *	<bean id="ds"
			class="org.apache.commons.dbcp.BasicDataSource"
			p:driverClassName="oracle.jdbc.driver.OracleDriver"
			p:url="jdbc:oracle:thin:@locahost:1521:XE"
			p:username="hr"
			p:password="happy"
		/>
	 */
	@Bean("ds") // 객체 생성
	public BasicDataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@locahost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
	}
	
	/*
	 * 	<bean id="ssf"
			class="org.mybatis.spring.SqlSessionFactoryBean"
			p:dataSource-ref="ds"
		/>
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() {
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}
	
	/*
	 * 	<bean id="mapper"
			class="org.mybatis.spring.mapper.MapperFactoryBean"
			p:sqlSessionFactory-ref="ssf"
			p:mapperInterface="com.sist.mapper.EmpMapper"
		/>
	 */
	@Bean("mapper")
	public MapperFactoryBean mapperFactoryBean() {
		MapperFactoryBean mapper=new MapperFactoryBean();
		mapper.setSqlSessionFactory(sqlSessionFactory());
		mapper.setMapperInterface(com.sist.mapper.EmpMapper.class)
	}
}
