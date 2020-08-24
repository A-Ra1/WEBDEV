package com.sist.dao;

import java.sql.*;
import java.util.*;

public class BoardDAO {

private Connection conn;
	
	// SQL 전송
	private PreparedStatement ps;
	
	// 오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 1. 실제 오라클과 연결해주는 드라이버 oracle.jdbc.driver.OracleDriver
	public BoardDAO () {
		
		try {
			
			// 드라이버를 이용해서 연결 => this드라이버
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
		} catch (Exception e) {

		}
		
	}
	public void getConnection() {
		
		try {
			
			conn=DriverManager.getConnection(URL,"hr","happy");
			
		} catch (Exception e) {
		}
	}
	
	public void disConnection() {
		
		try {

			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
		}
	}
	
	public ArrayList<BoardVO> boardAllData() {
		
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		
		try {
			
			getConnection();
			
			String sql="SELECT no, subject, name, regdate, hit FROM jsp_board ORDER BY no DESC";
			
			ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				BoardVO vo=new BoardVO();
				
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
			
			 list.add(vo);
				
			}
			
			rs.close();
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			disConnection();
			
		}
		
		return list;
	}
	
}
