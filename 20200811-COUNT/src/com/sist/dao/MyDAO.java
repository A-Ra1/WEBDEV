package com.sist.dao;

import java.sql.*;
import java.util.*;

public class MyDAO {

	// 연결 객체
	private Connection conn;
	
	// SQL 전송 객체
	private PreparedStatement ps;
	
	// URL (오라클 주소)
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 등록
	public MyDAO() {
		
		try {
			
			// 오라클을 연결하는 클래스 이름
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	// 연결
	public void getConnection() {
		
		try {
		
			conn=DriverManager.getConnection(URL,"hr","happy");
			
		} catch (Exception e) {

		}
		
	}
	
	// 해제
	public void disConnection () {
		
		try {
			
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
		}
		
	}
	
	// JDBC (원시소스) => DBCP => ORM
	public String isLogin(String ename, int empno) {
		
		String result="";
		
		try {
		
			getConnection();
			
			// SQL문장 전송 : 0이면 id가 존재하지 않는다, 1이면 id가 존재한다
			String sql="SELECT COUNT(*) FROM emp WHERE ename=?";
			ps=conn.prepareStatement(sql);
			
			// ?에 값을 채운다
			ps.setString(1, ename);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1); // 0 or 1
			rs.close();
			
			if(count==0) { // 사원의 이름이 존재하지 않는다
				
				result="NONAME";
				
			} else { // 사원의 이름이 존재
				
				sql="SELECT empno FROM emp WHERE ename=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, ename);
				rs=ps.executeQuery();
				rs.next(); // 데이터가 있는 위치로 커서 이동
				int db_empno=rs.getInt(1);
				rs.close();
				
				if(empno==db_empno) { // 로그인 성공한 상태
					
					result=ename;
					
				}else { // 사번이 틀린 상태
					
					result="NOSABUN";
							
				}
				
			}
				
			
		} catch (Exception e) {
		
			System.out.println(e.getMessage());
			
		} finally {
			
			disConnection();
		}
		
		return result;
				
	}
}
