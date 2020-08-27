<%@page import="com.sist.dao.ReplyBoardDAO"%>
<%@page import="com.sist.dao.ReplyBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 	// 값을 받아서 오라클에 추가
 	try{
 		
 		request.setCharacterEncoding("UTF-8");
 		
 		
 	} catch(Exception ex) {}
 	
 	// 목록으로 이동
 	String name=request.getParameter("name");
 	String subject=request.getParameter("subject");
 	String content=request.getParameter("content");
 	String pwd=request.getParameter("pwd");
 	
 	// DAO => INSERT
 	ReplyBoardVO vo=new ReplyBoardVO();
 	vo.setName(name);
 	vo.setSubject(subject);
 	vo.setContent(content);
 	vo.setPwd(pwd);
 	
 	ReplyBoardDAO dao=new ReplyBoardDAO();
 	
 	// dao => Insert
 	dao.boardInsert(vo);		
 	
	response.sendRedirect("list.jsp");
%>