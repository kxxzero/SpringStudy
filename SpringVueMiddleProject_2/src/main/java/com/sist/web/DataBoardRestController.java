package com.sist.web;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/*
 * Vue / React
 * 	=> Router => 데이터 통신(송수신)
 * 	=> Spring => 화면 변경
 */
import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;
@RestController // Vue와 데이터 통신
public class DataBoardRestController {
	 @Autowired
	 private DataBoardDAO dao;
	 
	 @GetMapping(value="databoard/list_vue.do", produces="text/plain;charset=UTF-8")
	 public String databoard_list(int page) throws Exception {
		 int rowSize=10;
		 int start=(rowSize*page)-(rowSize-1);
		 int end=rowSize*page;
		 List<DataBoardVO> list=dao.databoardListData(start, end);
		 ObjectMapper mapper=new ObjectMapper();
		 String json=mapper.writeValueAsString(list);
		 
		 return json;
	 }
	 
	 @GetMapping(value="databoard/page_vue.do", produces="text/plain;charset=UTF-8")
	 public String databoard_page(int page) throws Exception {
		 int totalpage=dao.databoardTotalPage();
		 Map map=new HashMap();
		 map.put("curpage", page);
		 map.put("totalpage", totalpage);
		 ObjectMapper mapper=new ObjectMapper();
		 String json=mapper.writeValueAsString(map); // curpage=1, totalpage=10
		 
		 return json;
	 }
	 
	 @PostMapping(value="databoard/insert_vue.do", produces="text/plain;charset=UTF-8")
	 public String databoard_insert(DataBoardVO vo, HttpServletRequest request) {
		 String result="";
		 try {
			 String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
			 path=path.replace("\\", File.separator); // separator : 운영체제에 따라 구분자(슬래쉬)가 자동으로 변환됨
			 // Hosting => AWS(리눅스)
			 File dir=new File(path);
			 if(!dir.exists()) {
				 dir.mkdir();
			 }
			 List<MultipartFile> list=vo.getFiles(); // 임시 저장
			 if(list==null) { // 업로드가 없는 상태
				 vo.setFilename("");
				 vo.setFilesize("");
				 vo.setFilecount(0); 
			 } else { // 업로드가 있는 상태
				 String filename="";
				 String filesize="";
				 for(MultipartFile mf:list) {
					 String name=mf.getOriginalFilename();
					 File file=new File(path+name);
					 mf.transferTo(file); // 업로드
					 
					 filename+=name+","; // a.jpg, b.jpg, ...
					 filesize+=file.length()+","; // ,로 저장							 
				 }
				 filename=filename.substring(0, filename.lastIndexOf(",")); // 맨 마지막에 위치한 ,를 삭제하는 역할 => Tonkenizer로 자르기 위해
				 filesize=filesize.substring(0, filesize.lastIndexOf(","));
				 vo.setFilename(filename);
				 vo.setFilesize(filesize);
				 vo.setFilecount(list.size());
			 }
			 dao.databoardInsert(vo);
			 result="yes";
		 }catch(Exception ex) {
			result=ex.getMessage();
		 }
		 
		 return result;
	 }
	 
	 @GetMapping(value="databoard/detail_vue.do", produces="text/plain;charset=UTF-8")
	 public String databoard_detail(int no) throws Exception {
		 DataBoardVO vo=dao.databoardDetailData(no);
		 ObjectMapper mapper=new ObjectMapper();
		 String json=mapper.writeValueAsString(vo);
		 
		 return json;
	 }
	 
	 @GetMapping(value="databoard/download.do")
	 public void databoard_download(String fn, HttpServletRequest request, HttpServletResponse response) {
		String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
		path=path.replace("\\", File.separator);
		
		try {
			File file=new File(path+fn);
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fn, "UTF-8"));
			response.setContentLength((int)file.length());
			// 다운로드 창을 보여줌
			
			// 서버에서 파일을 읽어옴
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			
			// 클라이언트 복사 영역
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream()); // 다운로드 받을 경로 지정
			
			int i=0;
			byte[] buffer=new byte[1024];
			while((i=bis.read(buffer, 0, 1024))!=-1) {
				// i: 읽은 바이트 수
				// -1 : EOF(끝)
			}
			bis.close();
			bos.close();
		}catch(Exception ex) {}
	 }
	 
}
