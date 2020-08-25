package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sun.javafx.geom.AreaOp.IntOp;

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
	
	public ArrayList<BoardVO> boardAllData(int page) {
		
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		
		try {
			
			getConnection();
			
			int rowSize=10;
			
			// BETWEEN start AND end
			int start=(page*rowSize)-(rowSize-1);
			// rownum => 1
			/*
			 * 1page 일때  1
			 * 2page 일때 11
			 */
			int end=page*rowSize;
			String sql="SELECT no, subject, name, regdate, hit, num "
					+"FROM (SELECT no, subject, name, regdate, hit, rownum as num "
					+"FROM (SELECT no, subject, name, regdate, hit "
					+"FROM jsp_board ORDER BY no DESC)) WHERE num BETWEEN ? AND ?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
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
	
	// 총페이지
	public int boardTotalPage() {
		
		int total=0;
		
		try {
			
			getConnection();
			
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM jsp_board";
			
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
			
		} finally {
			
			disConnection();
			
		}
		
		return total;
		
	}

	// 내용보기
	public BoardVO boardDetailData(int no) {
		
		BoardVO vo=new BoardVO();
		
		try {

			getConnection();
			
			// 조회수 증가 후 데이터 가져오기
			String sql="UPDATE jsp_board SET hit=hit+1 WHERE no=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			// 실행
			ps.executeUpdate();
			
			// 데이터 읽기
			sql="SELECT no, name, subject, content, regdate, hit FROM jsp_board "
					+"WHERE no=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			// 결과값 받기
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			
			rs.close();
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
		
		} finally {
			
			disConnection();
			
		}
		
		return vo;
	}
	
	// 새글 등록
	public void boardInsert(BoardVO vo) {
		
		try {
			
			getConnection();
			
			String sql="INSERT INTO jsp_board VALUES(jb_no_seq.nextval, ?, ?, ?, ?, SYSDATE, 0 )";
			
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate();
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
			
		} finally {
			
			disConnection();
			
		}
	}
	
	// 수정하기
	
public BoardVO boardUpdateData(int no) {
		
		BoardVO vo=new BoardVO();
		
		try {

			getConnection();
			
			// 데이터 읽기
			String sql="SELECT no, name, subject, content, regdate, hit FROM jsp_board "
					+"WHERE no=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			// 결과값 받기
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			
			rs.close();
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
		
		} finally {
			
			disConnection();
			
		}
		
		return vo;
	}

	// 수정
	public boolean boardUpdate(BoardVO vo) {
		
		boolean bCheck=false;
		
		try {
			
			getConnection();
			
			String sql="SELECT pwd FROM jsp_board WHERE no=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(vo.getPwd())) {
				
				bCheck=true;
				// 비밀번호가 같으면 수정
				sql="UPDATE jsp_board SET name=?, subject=?, content=? WHERE no=?";
				
				ps=conn.prepareStatement(sql);
				
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				
				ps.executeUpdate();
				
				
			} else {
				
				bCheck=false;
				
			}
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());

		} finally {
			
			disConnection();
			
		}
		
		return bCheck;
	}
}
