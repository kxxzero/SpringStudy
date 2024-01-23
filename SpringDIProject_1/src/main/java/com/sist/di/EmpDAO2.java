package com.sist.di;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
public class EmpDAO2 {

//	@Autowired
	private Connection conn;
	private PreparedStatement ps;
	private String url, username, password, driver;
	
	// 생성자를 이용하거나 set메소드를 이용
	public EmpDAO2(String driver) {
		try {
			
		} catch(Exception ex) {}
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	// 연결
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(url, username, password);
		} catch(Exception ex) {}
	}
	// 해제
	public void disConnection() {
		try {
			if(ps!=null) {
				ps.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch(Exception ex) {}
	}

}
