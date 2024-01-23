package com.sist.dao;
import java.util.*;
public class EmpVO {
	private int empno, sal, detpno;
	private String ename, job, dbday;
	private Date hiredate;
	
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	private DeptVO dvo=new DeptVO(); // 조인 => DeptVO 클래스를 따로 생성 후 불러오기 => 포함 클래스
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getDetpno() {
		return detpno;
	}
	public void setDetpno(int detpno) {
		this.detpno = detpno;
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
	public DeptVO getDvo() {
		return dvo;
	}
	public void setDvo(DeptVO dvo) {
		this.dvo = dvo;
	}
}
