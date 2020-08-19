package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.manager.MovieVO;
import com.sist.manager.NewsVO;

import sun.security.mscapi.CKeyPairGenerator.RSA;

public class MovieDAO {

	// ����
		private Connection conn; // ����Ŭ ���� Ŭ����
		
		// sql ������ ����
		private PreparedStatement ps;
		
		// ����Ŭ �ּ� ÷��
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		
		// ���� �غ�
		public MovieDAO() {
			
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
			} catch (Exception e) {
			
				System.out.println(e.getMessage());
				
			}
		}
		
		// ����/�ݱ� �ݺ� => ����� �ݺ��� ��� => �޼ҵ�� ó��
		public void getConnection () {
			
			try {

				conn=DriverManager.getConnection(URL,"hr","happy");
					
			} catch (Exception e) {
			}
			
		}
		
		public void disConnection () {
			
			try {
				
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
				
			} catch (Exception e) {
			}
		}
	// ���
	// 1. ���� => INSERT, UPDATE, DELETE : ������� ���� (void)
		public void movieInsert(MovieVO vo) {
			
			try {
				
				getConnection();
				String sql="INSERT INTO daum_movie VALUES("
						+"(SELECT NVL(MAX(NO)+1,1) FROM daum_movie),?,?,?,?,?,?,?,?,?,?,?)";
				
				ps=conn.prepareStatement(sql);
				
				ps.setInt(1, vo.getCateno());
				ps.setString(2, vo.getTitle());
				ps.setString(3, vo.getPoster());
				ps.setString(4, vo.getRegdate());
				ps.setString(5, vo.getGenre());
				ps.setString(6, vo.getGrade());
				ps.setString(7, vo.getActor());
				ps.setString(8, vo.getScore());
				ps.setString(9, vo.getDirector());
				ps.setString(10, vo.getStory());
				ps.setString(11, vo.getKey());
				
				// ���� ���
				ps.executeUpdate();
				
			} catch (Exception e) {

				System.out.println(e.getMessage());
				
			}  
//			
//			finally {
//				
//				disConnection();
//				
//			}
			
			
			
		}
		
		public void newsInsert(NewsVO vo){
			
			try {
				
				getConnection();
				
				String sql="INSERT INTO daum_news VALUES (?,?,?,?,?)";
				
				ps=conn.prepareStatement(sql);
				
				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getPoster());
				ps.setString(3, vo.getLink());
				ps.setString(4, vo.getContent());
				ps.setString(5, vo.getAuthor());
				
				ps.executeUpdate();
				
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
				
			} finally {
				
				disConnection();
				
			}
			
		}
		public ArrayList<MovieVO> movieListData(int cno) {
			
			ArrayList<MovieVO> list=new ArrayList<MovieVO>();
			
			try {
				// ����
				getConnection();
				
				// SQL ����
				String sql="SELECT poster, title, no FROM daum_movie WHERE cateno=?";
				
				ps=conn.prepareStatement(sql);
				
				ps.setInt(1, cno);
				
				ResultSet rs=ps.executeQuery();
				while (rs.next()) {
					
					MovieVO vo=new MovieVO();
					vo.setPoster(rs.getString(1));
					vo.setTitle(rs.getString(2));
					vo.setNo(rs.getInt(3));
					
					// ArrayList�� ÷��
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
		
		public ArrayList<NewsVO> newsListData() {
			
			ArrayList<NewsVO> list=new ArrayList<NewsVO>();
			
			try {
				
				getConnection();
				
				String sql="SELECT title, poster, content, link, author FROM daum_news";
				
				ps=conn.prepareStatement(sql);
				
				ResultSet rs=ps.executeQuery();
				
				while (rs.next()) {
					
					NewsVO vo=new NewsVO();
					vo.setTitle(rs.getString(1));
					vo.setPoster(rs.getString(2));
					vo.setContent(rs.getString(3));
					vo.setLink(rs.getString(4));
					vo.setAuthor(rs.getString(5));
					
					list.add(vo);
				}
				
				rs.close();
				
			} catch (Exception e) {
			
			} finally {
				
				disConnection();
			}
			return list;
		}
}
