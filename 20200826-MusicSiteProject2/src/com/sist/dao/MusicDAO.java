package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sist.manager.MusicVO;

public class MusicDAO {

	private Connection conn;
	
	// SQL 전송
	private PreparedStatement ps;
	
	// 오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 1. 실제 오라클과 연결해주는 드라이버 oracle.jdbc.driver.OracleDriver
	public MusicDAO () {
		
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

	public void musicInsert(MusicVO vo) {
		
		try {
			
			// 연결 
			getConnection();
			
			// SQL문장
			String sql="INSERT INTO music2 VALUES ((SELECT NVL(MAX(mno)+1,1) FROM music2), ?, ?, ?, ?, ?)";
			
			ps=conn.prepareStatement(sql);
			
			// ?에 값을 채운다
			ps.setInt(1, vo.getCateno());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getPoster());
			ps.setString(4, vo.getSinger());
			ps.setString(5, vo.getAlbum());
			
			ps.executeUpdate(); // INSERT 문장 실행 => 끝나고 나면 자동 COMMIT
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
			
		} finally {
			
			disConnection();
			
		}
		
	
   }
	
	public ArrayList<String> musicGenreAllData() {
		
		ArrayList<String> list=new ArrayList<String>();
		
		try {
			
			getConnection();
			
			String sql="SELECT genre FROM music_genre2 ORDER BY no";
			
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				String genre=rs.getString(1);
				list.add(genre);
				
			}
			
			rs.close();
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			
			disConnection();
			
		}
		
		return list;
	}
	
	public ArrayList<MusicVO> musicAllData(int cateno, int page) {
		
		ArrayList<MusicVO> list=new ArrayList<MusicVO>();
		
		try {
			
			// Subquery 
			/*
			 *  SELECT ename, .. , (SELECT ~)
			 *  FROM (SELECT ~)
			 */
			
			getConnection();
			
			String sql="SELECT mno, title, poster, singer, album, RANK() OVER(ORDER BY mno), num "
					+"FROM (SELECT mno, title, poster, singer, album, rownum as num "
					+"FROM (SELECT mno, title, poster, singer, album "
					+"FROM music2 WHERE cateno=? ORDER BY mno)) WHERE num BETWEEN ? AND ?";
			
			int rowSize=30;
			int start=(rowSize*page)-(rowSize-1);
			// rownum의 시작번호는 (1)
			
			int end=rowSize*page;
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cateno);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			// 실행
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				MusicVO vo=new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setSinger(rs.getString(4));;
				vo.setAlbum(rs.getString(5));
				vo.setRank(rs.getInt(6));
				
				list.add(vo);
				
			}
			
			rs.close();
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
			
		} finally {
			
			disConnection();
			
		}
		
		return list;
	}
	
	public String musicGetGenre(int cateno) {
		
		String genre="";
		
		try {
			
			getConnection();
			
			String sql="SELECT genre FROM music_genre2 WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cateno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			genre=rs.getString(1);
			rs.close();
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			
			disConnection();
	
		}
		return genre;
	}
}
