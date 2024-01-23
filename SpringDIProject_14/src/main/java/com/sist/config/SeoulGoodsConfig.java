package com.sist.config;

import java.util.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration

@ComponentScan(basePackages="com.sist.*")

@MapperScan(basePackages="com.sist.mapper")
public class SeoulGoodsConfig {

	@Bean("ds")
	public BasicDataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		
		// setter
		
		
		public SqlSessionFactory sqlessionFactoryBean() {
			
		}
	}
}
