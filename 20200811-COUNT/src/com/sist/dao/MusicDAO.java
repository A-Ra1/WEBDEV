package com.sist.dao;

import java.util.*;
import java.sql.*;

public class MusicDAO {

	 // 오라클 연결
	private Connection conn;
	
	// SQL문장을 오라클로 전송
	private PreparedStatement ps;
	
	// 오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 설치
	public MusicDAO() {
		
		// 생성자 => 멤버변수의 초기화 , 네트워크 서버 연결
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	// 연결
	public void getConnection() {
		
		try {
			// conn hr/happy
			conn=DriverManager.getConnection(URL,"hr","happy");
			
		} catch (Exception e) {
		}
	}
	
	// 연결 종료
	public void disConnection() {
		
		try {
			
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
		}
	}
	
	// SQL 문장 전송 => 200개의 데이터를 요청
	public ArrayList<MusicVO> musicAllData() {
		
		ArrayList<MusicVO> list=new ArrayList<MusicVO>();
		try {
			
			// 오라클 연결
			getConnection();
			
			// SQL문장 전송
			String sql="SELECT mno, poster, title, singer, album "
					+"FROM genie_music "+"ORDER BY mno" ;
			ps=conn.prepareStatement(sql); // executeQuery() 이전에는 결과값을 받을 수 없다
			
			// 결과값 받기
			ResultSet rs=ps.executeQuery(); // rs에 전체 결과값
			while(rs.next()) { // next() 커서의 위치 변경 => 더이상 데이터가 없으면 false가 되며 종료
							   // previous() 커서의 위치 변경 => 역순 출력
				
				MusicVO vo=new MusicVO(); // 200개를 따로 저장한다
				
				vo.setMno(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setSinger(rs.getString(4));
				vo.setAlbum(rs.getString(5));
				
				// 200개를 모아서 브라우저로 전송
				list.add(vo);
			}
			rs.close();
			
			// ArrayList에 값 채우기
			
			
		} catch (Exception e) {
			
			// Error시 종류 확인 
			System.out.println(e.getMessage());
		}
		
		finally {
			// 서버 종료
			disConnection();
		}
		return list;
	}
	// 상세보기
	public MusicVO musicDetailData(int mno) {
		
		MusicVO vo=new MusicVO();
		
		try {
			
			getConnection();
			String sql="SELECT mno, title, singer, album, poster, key FROM genie_music "
					+"WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			// 값을 채운다
			vo.setMno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setSinger(rs.getString(3));
			vo.setAlbum(rs.getString(4));
			vo.setPoster(rs.getString(5));
			vo.setKey(rs.getString(6));
			
			rs.close();
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			
			disConnection();
		}
		
		return vo;
		
	}
}
