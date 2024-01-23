package com.sist.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.*;

@Configuration(basePackages="com.sist.*")
@ComponentScan(basePackages="com.sist.mapper")
public class FoodConfig {
	@Bean("ds")
	public BasicDataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		
	}
	
	@Bean("ssf")
	
	
}
