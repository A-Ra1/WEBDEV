package com.sist.dao;

import java.util.*;
import java.sql.*;

public class ReplyBoardDAO {

	private Connection conn;
	
	// SQL ����
	private PreparedStatement ps;
	
	// ����Ŭ �ּ�
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 1. ���� ����Ŭ�� �������ִ� ����̹� oracle.jdbc.driver.OracleDriver
	public ReplyBoardDAO () {
		
		try {
			
			// ����̹��� �̿��ؼ� ���� => this����̹�
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
	
	// 1. ��� ��� = ������ ������
	public ArrayList<ReplyBoardVO> boardListData(int page) {
		
		ArrayList<ReplyBoardVO> list=new ArrayList<ReplyBoardVO>();
		
		try {
			
			getConnection();
			String sql="SELECT no, subject, name, regdate, hit, group_tab, num "
						+"FROM (SELECT no, subject, name, regdate, hit, group_tab, rownum as num "
						+"FROM (SELECT no, subject, name, regdate, hit, group_tab "
						+"FROM replyBoard ORDER BY group_id DESC, group_step ASC)) "
						+"WHERE num BETWEEN ? AND ?";
			
			ps=conn.prepareStatement(sql);
			
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			// ?�� �� ä���
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			// ���� �Ŀ� ����� �б�
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				ReplyBoardVO vo=new ReplyBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			
			disConnection();
			
		}
		
		return list;
		
	}
	
	// 1-1 ��ü ����
	public int boardRowCount() {
		
		int count=0;
		try {
			
			getConnection();
			String sql="SELECT COUNT(*) FROM replyBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			rs.next();
			count=rs.getInt(1);
			rs.close();
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
			
		} finally {
		
			disConnection();
			
		}
		
		return count;
	}
	
	// 2. �󼼺���
	// 3. ���� ��� => INSERT
	public void boardInsert(ReplyBoardVO vo) {
		
		try {
			
			getConnection();
			// Sequence X
			String sql="INSERT INTO replyBoard(no, name, subject, content, pwd, group_id) " 
					+"VALUES(rb_no_seq.nextval, ?, ?, ?, ?, "
					+"(SELECT NVL(MAX(group_id)+1, 1) FROM replyBoard))";
			
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
	// 4. �亯�ϱ� => SQL => ���ļ� ó�� (��������)
	// 5. �����ϱ� => UPDATE
	// 6. �����ϱ� => SQL => ���ļ� ó�� (��������)
	// 7. ã�� => LIKE, REGEXP_LIKE
}
