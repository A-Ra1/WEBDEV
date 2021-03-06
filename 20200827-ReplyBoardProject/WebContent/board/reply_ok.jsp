<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	try {
		
		request.setCharacterEncoding("UTF-8"); // 한글 변환 코드 (디코딩)
		
	} catch (Exception ex) {}
	
	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	String pno=request.getParameter("pno");
	String strPage=request.getParameter("page");
	
	ReplyBoardVO vo=new ReplyBoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	// DAO 연결
	ReplyBoardDAO dao=new ReplyBoardDAO();
	
	// 답변 메소드 호출
	dao.boardReplyInsert(Integer.parseInt(pno), vo);
	
	// 이동 (답변한 페이지의 목록으로)
	response.sendRedirect("list.jsp?page="+strPage);	
	
%>