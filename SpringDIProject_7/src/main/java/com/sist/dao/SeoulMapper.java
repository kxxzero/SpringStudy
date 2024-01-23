package com.sist.dao;

/*
 * VO + DAO + Mapper => 한 세트
 */

import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface SeoulMapper {
	@Select("SELECT no, title, msg, address "
			+ "FROM seoul_nature "
			+ "ORDER BY no ASC")
	public List<SeoulVO> natureListData();
	
	@Select("SELECT no, title, address, msg "
			+ "FROM seoul_nature "
			+ "WHERE no=#{no}")
	public SeoulVO natureDetailData(int no);
	
	/*	<기존 형태의 코드>
	 * 	public SeoulVO natureDetailData(int no) {
	 * 	SeoulVO vo=new SeoulVO();
	 * 	try {
	 * 		getConnection();
	 * 		String sql="SELECT ~ 
	 * 				WHERE no=?"
	 * 		ps=conn.preparedStatement(sql);
	 * 		ps.setInt(1,no);
	 * 		ResultSet rs=ps.executeQuery();
	 * 		rs.next();
	 * 		vo.setNo(rs.getInt(1));
	 * 		vo.setTitle(rs.getString(2));
	 * 		vo.setMsg(rs.getString(3));
	 * 		vo.setAddress(rs.getString(4));
	 * 	} catch(Exception ex) {
	 * 		ex.printstackTrace();
	 * 	} finally {
	 * 		disConnection();
	 * 	}
	 */
	
	@Select("SELECT no, title, msg, address "
			+ "FROM seoul_nature "
			+ "WHERE title LIKE '%'||#{title}||'%'") // Like문은 '||' 사용
	public List<SeoulVO> natureFindData(String title); // 여러 목록 출력 시에는 list
}

