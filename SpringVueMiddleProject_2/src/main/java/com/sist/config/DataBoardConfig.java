package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
 * 셋팅 : DispatcherServlet이 인식할 수 있도록 알려주는 것
 * 	예)	
 * 	주문		==========>		서빙		==========>		주방(Model)
 * 	음식		<==========		배달		<========== HandlerMapping
 * 			ViewResolver
 * 						 ┌ 음식 직접 수령 : JSP 생성 없이 처리 => AJAX, MVueJS
 * 			ViewResolver │
 * 						 └ 음식 대리 수령 : 새로운 JSP 생성 => 화면 깜빡임
 * 	============================================================
 * 		최근 추세		
 * 			JSP : Front / Back 서로 간의 매칭이 어려움 => JSP 대신 HTML 사용
 * 			SQL : JPA 사용 => 장점 : 페이지가 자동으로 나눠짐
 * 				예) @Table(name="board")
 * 					class Board { 
 * 						@Id
 * 						private int no;
 * 					}
 * 			*** 최근에는 대부분 Annotation을 주로 사용하여 코드를 작성
 * 			Mobx : React(Spring)
 */

@Configuration // 환경 설정 => 메모리 할당

// <context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages="com.sist.*")

// <mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
@MapperScan(basePackages= {"com.sist.mapper"})

// <aop:aspectj-autoproxy/>
public class DataBoardConfig implements WebMvcConfigurer{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
		/* WebMvcConfigurer.super.configureDefaultServletHandling(configurer); */
	}
	// => HandlerMapping
	
	/*
	 * <bean id="viewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		p:prefix="/"
		p:suffix=".jsp"
		scope="prototype"
	/>
	 */
	@Bean("viewResolver")
	/* @Scope("prototype") */
	public ViewResolver viewResolver() {
		InternalResourceViewResolver ir=new InternalResourceViewResolver();
		ir.setPrefix("/");
		ir.setSuffix(".jsp");
		
		return ir;
	}
	
	@Bean("multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(100*1024*1024); // 100MB(기본값은 5MB)
		
		return multipartResolver;
	}
	
	
	/*	DB 관련	*/
	/*
	 * <bean id="ds"
		class="org.apache.commons.dbcp.BasicDataSource"
		p:driverClassName="#{db['driver']}"
		p:url="#{db['url']}"
		p:username="#{db['username']}"
		p:password="#{db['password']}"
		p:maxActive="#{db['maxActive']}"
		p:maxIdle="#{db['maxIdle']}"
		p:maxWait="#{db['maxWait']}"
	/>
	 */
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		ds.setMaxActive(50);
		ds.setMinIdle(20);
		ds.setMaxWait(-1);
		
		return ds;
	}
	
	/*
	 * <bean id="ssf"
		class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
	/>
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception { // return ssf.getoObject() 입력하기 위한 예외 처리
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		
		return ssf.getObject();
	}

}
