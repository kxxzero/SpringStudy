package com.sist.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration

@MapperScan(basePackages = "com.sist.mapper")
public class CustomerConfig {

}
