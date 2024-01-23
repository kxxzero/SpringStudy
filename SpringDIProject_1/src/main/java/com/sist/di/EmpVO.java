package com.sist.di;

// 사용자 정의 데이터형 => 스프링에 의존하지 않음 => 필요할때마다 가져다가 사용
/*
 * 스프링은 모든 클래스를 등록하나 VO, Bean, DAO는 제외 함
 */
import java.util.*;
public class EmpVO {
	private int empno;
	private String ename, job;
	private Date hiredate;
	private int sal;
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
}
