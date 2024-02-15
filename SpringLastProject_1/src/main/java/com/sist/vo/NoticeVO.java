package com.sist.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeVO {
	private int no, hit;
	private String name, subject, content, dbday;
	private Date regdate;
}
