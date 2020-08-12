package com.sist.dao;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=EUC-KR"); // 한글변환
		
		// 값을 받는다
		String mno=request.getParameter("mno");
		MusicDAO dao=new MusicDAO();
		MusicVO vo=dao.musicDetailData(Integer.parseInt(mno));
		
		PrintWriter out=response.getWriter(); // out의 HTML을 읽어라
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>&lt;"+vo.getTitle()+"&gt; 상세보기<h1>"); // &lt:< , &gt:>
		out.println("<table width=700>");
		out.println("<tr>");
		out.println("<td>");
		// 동영상 <iframe> => youtube, 
		// 저장된 동영상 => <video>
		out.println("<iframe src=http://youtube.com/embed/"+vo.getKey()+" width=700 height=400></iframe>"); 
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<table width=700>");
		out.println("<tr>");
		out.println("<td width=45% rowspan=4>"); // rowspan : row 통합
		out.println("<img src="+vo.getPoster()+" width=100%>");
		out.println("</td>");
		
		out.println("<td>");
		out.println(vo.getTitle());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>");
		out.println(vo.getSinger());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>");
		out.println(vo.getAlbum());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>");
		out.println("<a href=MusicServlet>목록</a>"); // 화면 이동 <a>태그
		out.println("</td>");
		out.println("</tr>");
	
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
	
		
		
		
	}

}
