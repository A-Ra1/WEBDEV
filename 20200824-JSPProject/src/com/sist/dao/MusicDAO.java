package com.sist.dao;
/*
 *  ��� (���), ���� (������ ������), ����ó�� (����Ŭ ����)
 *  ����Ŭ ���� (ArrayList) => add(), get(), size()
 *  => ��� (for-each)
 */
import java.sql.*; // Connection, PreparedStatement, ResultSet
import java.util.*; // ArrayList

public class MusicDAO {

	// ����
	private Connection conn;
	
	// SQL ����
	private PreparedStatement ps;
	
	// ����Ŭ �ּ�
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 1. ���� ����Ŭ�� �������ִ� ����̹� oracle.jdbc.driver.OracleDriver
	public MusicDAO () {
		
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
	// ��� => MusicTop200
	
	public ArrayList<MusicVO> musicAllData() {
		
		ArrayList<MusicVO> list=new ArrayList<MusicVO>();

		try {

			getConnection();
			
			// SQL���� ����
			String sql="SELECT mno, title, singer, album, poster FROM genie_music ORDER BY mno";
			
			ps=conn.prepareStatement(sql);
			
			// ����� �ޱ�
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
	
	// ��ȭ => ���
	
	public ArrayList<MovieVO> movieAllData() {
		
		ArrayList<MovieVO> list=new ArrayList<MovieVO>();
		
		try {
			
			getConnection();
			
			String sql="SELECT no, title, poster, genre, grade FROM daum_movie WHERE cateno=1 "
					   +"ORDER BY no";
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
	
	public ArrayList<MovieVO> movieAllData(int page) {
		
		ArrayList<MovieVO> list=new ArrayList<MovieVO>();
		
		try {
			
			getConnection();
			
			int rowSize=15;
			
			// BETWEEN start AND end
			int start=(page*rowSize)-(rowSize-1);
			// rownum => 1
			/*
			 * 1page �϶�  1
			 * 2page �϶� 11
			 */
			int end=page*rowSize;
			String sql="SELECT no, title, poster, genre, grade, num "
					+"FROM (SELECT no, title, poster, genre, grade, rownum as num "
					+"FROM (SELECT no, title, poster, genre, grade "
					+"FROM daum_movie WHERE cateno=1 ORDER BY no)) WHERE num BETWEEN ? AND ?";
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
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
	
	// ��������
	public int movieTotalPage() {
		
		int total=0;
		
		try {
			
			getConnection();
			
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM daum_movie WHERE cateno=1";
			
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
}
