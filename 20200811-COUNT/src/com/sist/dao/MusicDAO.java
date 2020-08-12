package com.sist.dao;

import java.util.*;
import java.sql.*;

public class MusicDAO {

	 // ����Ŭ ����
	private Connection conn;
	
	// SQL������ ����Ŭ�� ����
	private PreparedStatement ps;
	
	// ����Ŭ �ּ�
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// ����̹� ��ġ
	public MusicDAO() {
		
		// ������ => ��������� �ʱ�ȭ , ��Ʈ��ũ ���� ����
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	// ����
	public void getConnection() {
		
		try {
			// conn hr/happy
			conn=DriverManager.getConnection(URL,"hr","happy");
			
		} catch (Exception e) {
		}
	}
	
	// ���� ����
	public void disConnection() {
		
		try {
			
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
		}
	}
	
	// SQL ���� ���� => 200���� �����͸� ��û
	public ArrayList<MusicVO> musicAllData() {
		
		ArrayList<MusicVO> list=new ArrayList<MusicVO>();
		try {
			
			// ����Ŭ ����
			getConnection();
			
			// SQL���� ����
			String sql="SELECT mno, poster, title, singer, album "
					+"FROM genie_music "+"ORDER BY mno" ;
			ps=conn.prepareStatement(sql); // executeQuery() �������� ������� ���� �� ����
			
			// ����� �ޱ�
			ResultSet rs=ps.executeQuery(); // rs�� ��ü �����
			while(rs.next()) { // next() Ŀ���� ��ġ ���� => ���̻� �����Ͱ� ������ false�� �Ǹ� ����
							   // previous() Ŀ���� ��ġ ���� => ���� ���
				
				MusicVO vo=new MusicVO(); // 200���� ���� �����Ѵ�
				
				vo.setMno(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setSinger(rs.getString(4));
				vo.setAlbum(rs.getString(5));
				
				// 200���� ��Ƽ� �������� ����
				list.add(vo);
			}
			rs.close();
			
			// ArrayList�� �� ä���
			
			
		} catch (Exception e) {
			
			// Error�� ���� Ȯ�� 
			System.out.println(e.getMessage());
		}
		
		finally {
			// ���� ����
			disConnection();
		}
		return list;
	}
	// �󼼺���
	public MusicVO musicDetailData(int mno) {
		
		MusicVO vo=new MusicVO();
		
		try {
			
			getConnection();
			String sql="SELECT mno, title, singer, album, poster, key FROM genie_music "
					+"WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			// ���� ä���
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
