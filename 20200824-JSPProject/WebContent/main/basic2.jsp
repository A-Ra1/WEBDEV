<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.text.*"%>
<%--@ page import="java.util.*" --%>    
<%--@ page import="java.text.*" --%>   
<%--
	** 지정된 속성에 값을 채운다
	<%@ page 속성="값" 속성="값"...
 --%>
 <%--
	JSP 시작점
	지시자
		1. page : JSP의 기본 정보
		  = contentType="text/html; charset=UTF-8"
		    ===========
		    브라우저에 HTML을 전송한다 (브라우저에서 HTML파싱준비)
		    브라우저 실행   contentType="text/html" => 화면에 출력
		             contentType="text/xml" => 문서 저장
		             charset=UTF-8 => charset=ISO-8859_1
		             						  ==========
		             						   ASC (영문)
		2. import : 이미 만들어진 클래스를 읽어올 때 사용
					(라이브러리 로드)       
					pageEncoding="UTF-8" import="java.util.*"%>    
		3. errorPage="jsp파일 등록" => error시 이동			   						   
  --%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>