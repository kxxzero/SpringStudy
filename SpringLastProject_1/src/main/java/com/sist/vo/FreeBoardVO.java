package com.sist.vo;

import java.util.Date;
/*
 *  NO                                        NOT NULL NUMBER
 NAME                                      NOT NULL VARCHAR2(51)
 SUBJECT                                   NOT NULL VARCHAR2(4000)
 CONTENT                                            CLOB
 PWD                                  	   NOT NULL VARCHAR2(10)
 REGDATE                                            DATE
 HIT                                                NUMBER
 */
import lombok.Data;

public class FreeBoardVO {
	private int no, hit;
	private String name, subject, content, pwd, dbday;
	private Date regdate;
}
