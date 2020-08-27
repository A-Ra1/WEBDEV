<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <center>
  <h1>화면 출력</h1>
  <%
   // 값이 넘어올때는 숫자, 문자 모두 '문자열' 
   // id=admin => request에 묶여서 들어온다
   String id=request.getParameter("id");
   String pwd=request.getParameter("pwd");
   String name=request.getParameter("name");
  %>
  <%=id %>
  <br>
  <%=pwd %>
  <br>
  <%=name %>
 </center>
</body>
</html>