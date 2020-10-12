package com.sist.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class BoardDAO {

	// xml을 파싱해주는 클래스 => SqlSessionFactory
	private static SqlSessionFactory ssf;
	
	// 구동하기 전에 자동으로 파싱을 한다 (초기화블럭)
	static {
		
		// 오류 처리 => Mybatis (컴파일 오류X => 에러 발생시 찾기 어렵다)
		try {
			
			// IO => 파일 경로, 파일명이 틀릴 경우
			// 파일 읽기
			Reader reader=Resources.getResourceAsReader("Config.xml");
			
			// xml파싱
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
		SqlSession session=ssf.openSession(); // Connection 얻기
		list=session.selectList("boardListData", map);	
		session.close(); // Connection 반환
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
  			  ======= 리턴형         		  ====== 매개변수
  -->
  SELECT no, name, subject, content, regdate, hit
  FROM freeboard
  WHERE no=#{no}
 </select>  
	 * 
	 */
	
//  구현한 메소드는 여러개를 동시에 처리 가능
//	(조회수 증가, 데이터 가져오기)
	
	public static BoardVO boardDetailData(int no) {
		
		SqlSession session=ssf.openSession();
		// 조회수 증가
		session.update("hitIncrement", no);
		session.commit(); // 정상적으로 저장
		// 데이터 읽기
		BoardVO vo=session.selectOne("boardDetailData", no);
		// 반환
		session.close();
		return vo;
	}
}
