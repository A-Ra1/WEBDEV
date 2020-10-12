package com.sist.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class BoardDAO {

	// xml�� �Ľ����ִ� Ŭ���� => SqlSessionFactory
	private static SqlSessionFactory ssf;
	
	// �����ϱ� ���� �ڵ����� �Ľ��� �Ѵ� (�ʱ�ȭ��)
	static {
		
		// ���� ó�� => Mybatis (������ ����X => ���� �߻��� ã�� ��ƴ�)
		try {
			
			// IO => ���� ���, ���ϸ��� Ʋ�� ���
			// ���� �б�
			Reader reader=Resources.getResourceAsReader("Config.xml");
			
			// xml�Ľ�
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
		} catch (Exception e) {
			
			e.printStackTrace();

		}
		
	}
	/*
	 * <select id="boardListData" resultType="BoardVO" parameterType="hashmap">
  SELECT no, subject, name, regdate, hit, num
  FROM (SELECT no, subject, name, regdate, hit, rownum as num
  FROM (SELECT no, subject, name, regdate, hit
  FROM freeboard ORDER BY no DESC))
  WHERE num BETWEEN #{start} AND #{end}
 </select>
 <select id="boardTotalPage" resultType="int">
  SELECT CEIL(COUNT(*)/10.0) FROM freeboard
 </select>
	 * 
	 */
	
	//            resulttype    			parametertype
	public static List<BoardVO> boardListData(Map map) {
		
		List<BoardVO> list=new ArrayList<BoardVO>(); 
		SqlSession session=ssf.openSession(); // Connection ���
		list=session.selectList("boardListData", map);	
		session.close(); // Connection ��ȯ
		return list;
	}
	
	public static int boardTotalPage() {
		
		int total=0;
		SqlSession session=ssf.openSession();
		total=session.selectOne("boardTotalPage");
		session.close();
		return total;
	}
	
	/*
	 * <update id="hitIncrement" parameterType="int">
 UPDATE freeboard SET
 hit=hit+1
 WHERE no=#{no}
 </update>
 <select id="boardDetailData" resultType="BoardVO" parameterType="int">
  <!-- public BoardVO boardDetailData(int no) 
  			  ======= ������         		  ====== �Ű�����
  -->
  SELECT no, name, subject, content, regdate, hit
  FROM freeboard
  WHERE no=#{no}
 </select>  
	 * 
	 */
	
//  ������ �޼ҵ�� �������� ���ÿ� ó�� ����
//	(��ȸ�� ����, ������ ��������)
	
	public static BoardVO boardDetailData(int no) {
		
		SqlSession session=ssf.openSession();
		// ��ȸ�� ����
		session.update("hitIncrement", no);
		session.commit(); // ���������� ����
		// ������ �б�
		BoardVO vo=session.selectOne("boardDetailData", no);
		// ��ȯ
		session.close();
		return vo;
	}
}
