package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;
/*
 *  doget : 입력창
 *  dopost : 데이터베이스 연결
 */

@WebServlet("/BoardInsert")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	// 폼 작업 (화면출력)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// 브라우저에서 실행하는 화면 (HTML)
				// 브라우저에 알림 => HTML문서를 전송할 것이다
				response.setContentType("text/html;charset=EUC-KR");
				 
				// HTML을 브라우저로 전송시작
				PrintWriter out=response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
				out.println("<style type=text/css>");
				out.println(".row {margin:0px auto;width:500px}");
				out.println("h2 {text-align:center}");
				out.println("</style>");
				out.println("</head>");
				out.println("<body>");
				out.println("<div class=container>");
				out.println("<h2>글쓰기</h2>");
				out.println("<div class=row>");
				
				out.println("<form method=post action=BoardInsert>");
				// get : 데이터 공개,  post : 데이터 숨김
				
				out.println("<table class=\"table\">");
				out.println("<tr>");
				out.println("<td width=15% class=text-right>이름</td>");
				out.println("<td width=85%>");
				out.println("<input type=text size=15 class=input-sm name=name>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width=15% class=text-right>제목</td>");
				out.println("<td width=85%>");
				out.println("<input type=text size=45 class=input-sm name=subject>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width=15% class=text-right>내용</td>");
				out.println("<td width=85%>");
				out.println("<textarea cols=50 rows=10 name=content></textarea>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td width=15% class=text-right>비밀번호</td>");
				out.println("<td width=85%>");
				out.println("<input type=password size=10 class=input-sm name=pwd>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td colspan=2 class=text-center>");
				out.println("<input type=submit class=\"btn btn-sm btn-danger\" value=글쓰기>");
				out.println("<input type=submit class=\"btn btn-sm btn-info\" value=취소 onclick=\"javascript:history.back()\">");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("</form>");
				out.println("</div>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
				
	}

	// 데이터베이스 연결 => 요청처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			request.setCharacterEncoding("EUC-KR"); // 디코딩
			
		} catch (Exception e) {
		}
		
		
		// 사용자가 보낸값 : request(사용자가 요청한 값을 모아둔다) 서버 정보 : response
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
//		System.out.println("이름:"+name);
//		System.out.println("제목:"+subject);
//		System.out.println("내용:"+content);
//		System.out.println("비번:"+pwd);
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setPwd(pwd);
		vo.setContent(content);
		
		// DAO로 전송 -> 오라클 INSERT 
		BoardDAO dao=new BoardDAO();
		dao.boardInsert(vo);
		
		// 목록으로 이동
		// 최종적으로 보여주는 위치
		response.sendRedirect("BoardList");
		
	}

}
