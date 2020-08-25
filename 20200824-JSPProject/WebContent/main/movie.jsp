<%@page import="com.sist.dao.MusicDAO"%>
<%@page import="com.sist.dao.MovieVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.text.*"%>
<%

String strPage=request.getParameter("page");
if(strPage==null)
		strPage="1";


MusicDAO dao=new MusicDAO();
int curpage=Integer.parseInt(strPage);
int totalpage=dao.movieTotalPage();
ArrayList<MovieVO> list=dao.movieAllData(curpage);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <center>
   <h1>영화 목록</h1>
 <table width=800>
  <tr>
   <td>
    <input type=text id=keyword size=25>
   </td>
  </tr>
 </table>
 <table border=1 width=800 id="user-table">
  <tr>
   <th>번호</th>
   <th></th>
   <th>제목</th>
   <th>장르</th>
   <th>등급</th>
  </tr>
  <tbody>
  <%
  	for(MovieVO vo:list) {
  %> 		
  <tr>
	 <td><%=vo.getNo()%></td>
	 <td><img src=<%=vo.getPoster() %> width=30 height=30></td>
	 <td><%=vo.getTitle() %></td>
     <td><%=vo.getGenre() %></td>
     <td><%=vo.getGrade() %></td>
  </tr>		
  <% 		
  	}
  %>
  </tbody>
 </table>
  <table class="table_content" width=800>
    <tr>
     <td align=left></td>
     <td align="right">
      <a href="movie.jsp?page=<%=curpage>1?curpage-1:curpage%>">이전</a>
      <%=curpage %>page / <%=totalpage %> pages
      <a href="movie.jsp?page=<%=curpage<totalpage?curpage+1:curpage%>">다음</a>
     </td>
    </tr>
   </table>
 </center>
</body>
</html>