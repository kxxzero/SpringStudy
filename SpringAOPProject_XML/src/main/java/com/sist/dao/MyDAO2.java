package com.sist.dao;

public class MyDAO2 {
	public void select() {
		// before 위치 getConnection()
		System.out.println("오라클 => SELECT 문장 요청");
		// after 위치 disConnection()
	}
	
	public void insert() {
		System.out.println("오라클 => INSERT 문장 요청");
	}
	
	public void update() {
		System.out.println("오라클 => UPDATE 문장 요청");
	}
	
	public void delete() {
		System.out.println("오라클 => DELETE 문장 요청");
	}
}
