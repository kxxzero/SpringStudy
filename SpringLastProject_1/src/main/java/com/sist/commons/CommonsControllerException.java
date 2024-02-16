package com.sist.commons;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 공통 예외 처리

@ControllerAdvice // controller에서 발생하는 오류를 잡음
public class CommonsControllerException {
	@ExceptionHandler(RuntimeException.class)
	public void runTimeException(RuntimeException ex) {
		 System.out.println("===== RuntimeException 발생 =====");
		 ex.printStackTrace();
		 System.out.println("================================");
	}
	
	@ExceptionHandler(IOException.class)
	public void ioException(IOException ex) {
		 System.out.println("===== IOException 발생 =====");
		 ex.printStackTrace();
		 System.out.println("===========================");
	}
	
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex) {
		 System.out.println("===== SQLException 발생 =====");
		 ex.printStackTrace();
		 System.out.println("============================");
	}
	
}
