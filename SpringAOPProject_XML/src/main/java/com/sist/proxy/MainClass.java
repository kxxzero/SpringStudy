package com.sist.proxy;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sawon s=new Sawon();
		s.disPlay();
		
		Proxy p=new Proxy(s);
		p.display();
	}

}