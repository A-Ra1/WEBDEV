<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 response.sendRedirect("list.do");

// list.do => Controller => ListModel => list.jsp
// ListModel에서 request에 값을 담아서 넘겨준다
%>