package com.sist.dao;
/*
 *  제어문 (출력), 변수 (데이터 모으기), 예외처리 (오라클 연결)
 *  오라클 연결 (ArrayList) => add(), get(), size()
 *  => 출력 (for-each)
 */
import java.sql.*; // Connection, PreparedStatement, ResultSet
import java.util.*; // ArrayList

public class MusicDAO {

	// 연결
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
	// 기능 => MusicTop200
	
	public ArrayList<MusicVO> musicAllData() {
		
		ArrayList<MusicVO> list=new ArrayList<MusicVO>();

		try {

			getConnection();
			
			// SQL문장 전송
			String sql="SELECT mno, title, singer, album, poster FROM genie_music ORDER BY mno";
			
			ps=conn.prepareStatement(sql);
			
			// 결과값 받기
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {
			
				MusicVO vo=new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getNString(3));
				vo.setAlbum(rs.getString(4));
				vo.setPoster(rs.getString(5));

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
	
	// 영화 => 출력
	
	public ArrayList<MovieVO> movieAllData() {
		
		ArrayList<MovieVO> list=new ArrayList<MovieVO>();
		
		try {
			
			getConnection();
			
			String sql="SELECT no, title, poster, genre, grade FROM daum_movie WHERE cateno=1";
			ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				MovieVO vo=new MovieVO();
				
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setGenre(rs.getString(4));
				vo.setGrade(rs.getString(5));
				
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
