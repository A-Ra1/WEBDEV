<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
    
<%
	// 사용자가 보내준 게시물을 받는다
	String no=request.getParameter("no");

	BoardDAO dao=new BoardDAO();
	BoardVO vo=dao.boardDetailData(Integer.parseInt(no));
			
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
</head>
<body>
 <center>
  <h1>내용보기</h1>
   <table class="table_content" width=700>
    <tr>
     <th width=20%>번호</th>
      <td width=20% align="center"><%=vo.getNo() %></td>
     <th width=20%>작성일</th>
      <td width=20% align="center"><%=vo.getRegdate().toString() %></td>
    </tr>
     <tr>
     <th width=20%>이름</th>
      <td width=20% align="center"><%=vo.getName() %></td>
     <th width=20%>조회수</th>
      <td width=20% align="center"><%=vo.getHit() %></td>
    </tr>
     <tr>
     <th width=20%>제목</th>
      <td colspan="3" align="left"><%=vo.getSubject()%></td>
    </tr>
    <tr>
     <td colspan="4" height="200" valign="top"><pre><%=vo.getContent() %></pre></td>
    </tr>
    <tr>
     <td colspan="4" align="right">
      <a href="update.jsp?no=<%=vo.getNo()%>">수정</a>&nbsp;
      <a href="#">삭제</a>&nbsp;
      <a href="list.jsp">목록</a>
     </td>
    </tr>
   </table>
 </center>
</body>
</html>