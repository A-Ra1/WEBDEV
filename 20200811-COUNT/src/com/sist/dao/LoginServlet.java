package com.sist.dao;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 브라우저에 HTML을 전송
		response.setContentType("text/html;charset=EUC-KR");
		
		// HTML 제작
		PrintWriter out=response.getWriter();
		
		// response : 응답(전송) , request : 사용자가 보낸 데이터를 받는 경우
		out.println("<html>");
		
		/*
		 * <html>
		 *  <head>
		 *   => <style>
		 *   => <script> 이벤트
		 *  </head>
		 *  <body> 화면 출력
		 *  </body>
		 * </html>
		 * 
		 *  태그 : 지정된 태그만 사용가능
		 *  태그는 대소문자를 구분하지 않지만 소문자로 사용
		 */
		
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>Login</h1>");
		out.println("<form method=post action=LoginServlet>"); // form method=post : post를 호출, Default : get
		out.println("<table width=250>");
		out.println("<tr>");
		out.println("<td width=15% align=right>이름</td>"); // align : 정렬 , 15% : 테이블크기 250에 대한 15%
		out.println("<td width=85%>");
		out.println("<input type=text name=ename size=17>"); // 입력창
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=15% align=right>사번</td>"); 
		out.println("<td width=85%>");
		out.println("<input type=password name=empno size=17>"); 
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>"); // tr : 다음줄로 변경하는 역할, 데이터는 td나 th사이
		out.println("<td colspan=2 align=center>"); // 가로통합 : colspan, 세로통합 : rowspan
		out.println("<input type=submit value=로그인>"); // submit : 버튼
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청 처리
		response.setContentType("text/html;charset=EUC-KR"); 
		PrintWriter out=response.getWriter();
		
		String ename=request.getParameter("ename");
		String empno=request.getParameter("empno");
		
		MyDAO dao=new MyDAO();
		String result=dao.isLogin(ename.toUpperCase(), Integer.parseInt(empno));
		
//		System.out.println("이름:"+ename.toUpperCase()); // 대문자로 변환
//		System.out.println("사번:"+empno);
		
		if(result.equals("NONAME")) {
			
			out.println("<script>");
			out.println("alert(\"이름이 존재하지 않습니다\");");
			out.println("history.back();"); // 이전 페이지로 복귀
			out.println("</script>");
			
		} else if (result.equals("NOSABUN")) {
			
			out.println("<script>");
			out.println("alert(\"사번이 존재하지 않습니다\");");
			out.println("history.back();");
			out.println("</script>");
			
		} else {
			
//			out.println("<script>");
//			out.println("alert(\""+result+"님 메인으로 이동합니다\");");
//			out.println("</script>");
			response.sendRedirect("MusicServlet"); // 로그인 성공시 다른 페이지로 이동
			
		}
		
	}

}
