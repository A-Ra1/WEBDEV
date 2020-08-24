<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    errorPage="error.jsp"
    %>
    <%--
    JSP : Java Server Page => 서버에서 실행되는 자바파일
    ===
    JSP의 구성요소
    	1. 지시자
    	 = page : JSP파일에 대한 정보
    	 = taglib : 태그로 자바의 문법
    	 			=> 제어문 <c:forEach> <c:if>
    	  자바 + HTML (HTML기반 , 자바 첨부)
    	  자바 영역 <% %>
    	  <html>
    	   <body>
    	    <h1>게시판</h1>
    	     <ul>
    	     <%
    	       for(BoardVO vo:list)          
    	       {
    	     %>  
    	         <li>번호-제목-이름-작성일-조회수</li>
    	     <%
    	       }
    	     %>
    	     </ul>
    	    </body>
    	   </html> 
    	   
    	   
    	   <ul>
    	    <c:forEach items="list">
    	     <li>번호-제목-이름-작성일-조회수</li>
    	    </c:forEach>
    	   </ul>  
    	   
    	 = include : 특정 JSP안에 JSP를 첨부 		
    	 	
    	2. 자바 코딩 부분
    	 = 스크립트릿 <% %> : 일반 자바 코딩
    	 = 표현식 <%= %> : out.println() 화면에 값을 출력
    	 = 선언식 <%! %> : 전역변수, 메소드를 만들 경우 (사용 빈도 적음)
    	
    	3. 내장 객체 (8개 지원) : 미리 객체를 생성해놓고 필요시마다 사용이 가능하게 만들어준다
    	 = request : 사용자의 요청값을 받을 때
    	 = response : 서버에서 처리 후 응답할때
    	 = session
    	 = pageContext
    	 = page
    	 = config
    	 = exception
    	 = application
    	 
    	4. 표현식 (EL, JSTL)
    	5. MVC  
     --%>
<!DOCTYPE html>
<%
out.println("<html>");
out.println("<head>");
out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
out.println("<style type=text/css>");
out.println(".row{");
out.println("margin:0px auto;");
out.println("width:300px;");
out.println("}");
out.println("</style>");
out.println("</head>");
out.println("<body>");
out.println("<div class=container>");
out.println("<h1 class=text-center>로그인</h1>");
out.println("<div class=row>");
out.println("<form method=post action=Login>"); // Login이 가진 dopost를 호출
out.println("<table class=table>");
out.println("<tr>");
out.println("<td width=20% class=text-right>ID</td>");
out.println("<td width=\"75%\">");
out.println("<input type=text name=id size=15 class=input-sm>");
out.println("</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td width=20% class=text-right>Password</td>");
out.println("<td width=\"75%\">");
out.println("<input type=password name=pwd size=15 class=input-sm>");
out.println("</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td colspan=2 class=text-center>");
out.println("<input type=submit value=로그인  class=\"btn btn-sm btn-success\">");
out.println("<input type=button value=취소  class=\"btn btn-sm btn-danger\">");
out.println("</td>");
out.println("</tr>");
out.println("</table>");
out.println("</form>");
out.println("</div>");
out.println("</div>");
out.println("</body>");
out.println("</html>");
%>