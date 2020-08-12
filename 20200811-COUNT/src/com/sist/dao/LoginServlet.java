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
		
		// �������� HTML�� ����
		response.setContentType("text/html;charset=EUC-KR");
		
		// HTML ����
		PrintWriter out=response.getWriter();
		
		// response : ����(����) , request : ����ڰ� ���� �����͸� �޴� ���
		out.println("<html>");
		
		/*
		 * <html>
		 *  <head>
		 *   => <style>
		 *   => <script> �̺�Ʈ
		 *  </head>
		 *  <body> ȭ�� ���
		 *  </body>
		 * </html>
		 * 
		 *  �±� : ������ �±׸� ��밡��
		 *  �±״� ��ҹ��ڸ� �������� ������ �ҹ��ڷ� ���
		 */
		
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>Login</h1>");
		out.println("<form method=post action=LoginServlet>"); // form method=post : post�� ȣ��, Default : get
		out.println("<table width=250>");
		out.println("<tr>");
		out.println("<td width=15% align=right>�̸�</td>"); // align : ���� , 15% : ���̺�ũ�� 250�� ���� 15%
		out.println("<td width=85%>");
		out.println("<input type=text name=ename size=17>"); // �Է�â
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=15% align=right>���</td>"); 
		out.println("<td width=85%>");
		out.println("<input type=password name=empno size=17>"); 
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>"); // tr : �����ٷ� �����ϴ� ����, �����ʹ� td�� th����
		out.println("<td colspan=2 align=center>"); // �������� : colspan, �������� : rowspan
		out.println("<input type=submit value=�α���>"); // submit : ��ư
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// ��û ó��
		response.setContentType("text/html;charset=EUC-KR"); 
		PrintWriter out=response.getWriter();
		
		String ename=request.getParameter("ename");
		String empno=request.getParameter("empno");
		
		MyDAO dao=new MyDAO();
		String result=dao.isLogin(ename.toUpperCase(), Integer.parseInt(empno));
		
//		System.out.println("�̸�:"+ename.toUpperCase()); // �빮�ڷ� ��ȯ
//		System.out.println("���:"+empno);
		
		if(result.equals("NONAME")) {
			
			out.println("<script>");
			out.println("alert(\"�̸��� �������� �ʽ��ϴ�\");");
			out.println("history.back();"); // ���� �������� ����
			out.println("</script>");
			
		} else if (result.equals("NOSABUN")) {
			
			out.println("<script>");
			out.println("alert(\"����� �������� �ʽ��ϴ�\");");
			out.println("history.back();");
			out.println("</script>");
			
		} else {
			
//			out.println("<script>");
//			out.println("alert(\""+result+"�� �������� �̵��մϴ�\");");
//			out.println("</script>");
			response.sendRedirect("MusicServlet"); // �α��� ������ �ٸ� �������� �̵�
			
		}
		
	}

}
