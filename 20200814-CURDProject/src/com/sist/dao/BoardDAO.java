package com.sist.dao;

import java.util.*;
import java.sql.*;

public class BoardDAO {

	// ����
	private Connection conn; // ����Ŭ ���� Ŭ����
	
	// sql ������ ����
	private PreparedStatement ps;
	
	// ����Ŭ �ּ� ÷��
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// ���� �غ�
	public BoardDAO() {
		
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
	// 1. ��� (�Խ���) => SELECT
	public ArrayList<BoardVO> boardListData() {
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
	
		
		try {
			
			// ����
			
			getConnection();
			
			// �ֽ� ��ϵ� �Խù��� ���� ���
			// ORDER BY : ���� (�ӵ��� �ʴ�) => INDEX
			String sql="SELECT no, subject, name, regdate, hit FROM freeboard ORDER BY no DESC";
			
			// SQL���� ����
			ps=conn.prepareStatement(sql);
			
			// SQL���� �Ŀ� ����� �ޱ�
			ResultSet rs=ps.executeQuery();
			
			// ������� ArrayList�� ÷��
			while(rs.next()) { // ����� ù��° �ٺ��� ������ �ٱ��� �о�´�
				
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegtDate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				
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
	// 2. ���뺸�� => SELECT (WHERE)
	
	public BoardVO boardDetail(int no) {
	
		BoardVO vo=new BoardVO();
		
		try {
			
			getConnection();
			
			// SQL ���� ���� : ��ȸ�� ����
			String sql="UPDATE freeboard SET hit=hit+1 WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no); // ?�� ���� ä���
			// ����
			ps.executeUpdate();
			
			// ������ �� �����͸� ������ �´�
			sql="SELECT no, name, subject, content, regdate, hit FROM freeboard "
					+"WHERE NO=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegtDate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			
			rs.close();
			
		} catch (Exception e) {
	
			System.out.println(e.getMessage());
			
		} finally {
			
			disConnection();
			
		}
		
		return vo;
	}
	
	// 3. �۾��� => INSERT
	public void boardInsert(BoardVO vo) {
		
		try {
			
			getConnection();
			// ����
			// SQL ���� ���� => ����
			String sql="INSERT INTO freeboard(no, name, subject, content, pwd) "
					+"VALUES((SELECT NVL(MAX(no)+1,1) FROM freeboard),?,?,?,?)";
					
					// �����Ͱ� NULL�Ͻ� ��� ������ 0�̴� (��� �� ������ ������ ���)
			
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate(); // COMMIT ���� => INSERT, UPDATE, DELETE
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
			
		} finally {
			
			disConnection();
		}
		
	}
	
	// 4. �ۼ��� => UPDATE
	// 5. �ۻ��� => DELETE
	// 6. ã�� => SELECT (LIKE)
}
