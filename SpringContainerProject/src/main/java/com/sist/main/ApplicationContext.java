package com.sist.main;

import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
public class ApplicationContext {
	private Map map=new HashMap();
	public ApplicationContext(String path) {
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(new File(path));
			Element beans=doc.getDocumentElement();
			NodeList list=doc.getElementsByTagName("bean");
			for(int i=0; i<list.getLength(); i++) {
				Element bean=(Element)list.item(i); // 아이템을 태그로 하나씩 가져옴
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				Class clsName=Class.forName(cls);
				Object obj=clsName.getDeclaredConstructor().newInstance();
				map.put(id, obj);
				// map에 저장하는 순간 주소값 고정
			}
		} catch(Exception ex) {}
	}
	
	public Object getBean(String key) {
		
		return map.get(key);
	}
}
