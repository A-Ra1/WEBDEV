<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    errorPage="error.jsp"
    %>
    <%--
    JSP : Java Server Page => �������� ����Ǵ� �ڹ�����
    ===
    JSP�� �������
    	1. ������
    	 = page : JSP���Ͽ� ���� ����
    	 = taglib : �±׷� �ڹ��� ����
    	 			=> ��� <c:forEach> <c:if>
    	  �ڹ� + HTML (HTML��� , �ڹ� ÷��)
    	  �ڹ� ���� <% %>
    	  <html>
    	   <body>
    	    <h1>�Խ���</h1>
    	     <ul>
    	     <%
    	       for(BoardVO vo:list)          
    	       {
    	     %>  
    	         <li>��ȣ-����-�̸�-�ۼ���-��ȸ��</li>
    	     <%
    	       }
    	     %>
    	     </ul>
    	    </body>
    	   </html> 
    	   
    	   
    	   <ul>
    	    <c:forEach items="list">
    	     <li>��ȣ-����-�̸�-�ۼ���-��ȸ��</li>
    	    </c:forEach>
    	   </ul>  
    	   
    	 = include : Ư�� JSP�ȿ� JSP�� ÷�� 		
    	 	
    	2. �ڹ� �ڵ� �κ�
    	 = ��ũ��Ʈ�� <% %> : �Ϲ� �ڹ� �ڵ�
    	 = ǥ���� <%= %> : out.println() ȭ�鿡 ���� ���
    	 = ����� <%! %> : ��������, �޼ҵ带 ���� ��� (��� �� ����)
    	
    	3. ���� ��ü (8�� ����) : �̸� ��ü�� �����س��� �ʿ�ø��� ����� �����ϰ� ������ش�
    	 = request : ������� ��û���� ���� ��
    	 = response : �������� ó�� �� �����Ҷ�
    	 = session
    	 = pageContext
    	 = page
    	 = config
    	 = exception
    	 = application
    	 
    	4. ǥ���� (EL, JSTL)
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
out.println("<h1 class=text-center>�α���</h1>");
out.println("<div class=row>");
out.println("<form method=post action=Login>"); // Login�� ���� dopost�� ȣ��
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
out.println("<input type=submit value=�α���  class=\"btn btn-sm btn-success\">");
out.println("<input type=button value=���  class=\"btn btn-sm btn-danger\">");
out.println("</td>");
out.println("</tr>");
out.println("</table>");
out.println("</form>");
out.println("</div>");
out.println("</div>");
out.println("</body>");
out.println("</html>");
%>