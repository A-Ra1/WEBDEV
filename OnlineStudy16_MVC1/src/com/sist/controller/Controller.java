package com.sist.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/////// xml �Ľ�
import javax.xml.parsers.*;
import org.w3c.dom.*;
///////
import com.sist.model.*;
// ���� (Model ==== JSP)
/*
 * Controller ��� => web.xml
 */
import java.util.*;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
 /*
  *  ���� ����
  *  = �޸� �Ҵ� (������ ȣ��)
  *  = init() : ���� �б�, ���� ����
  *  = service() : ����ڰ� ��û�� ó���ϴ� �޼ҵ�
  *    ======== get / post => ���ÿ� ó���� ������ �޼ҵ�
  *    get : doget()
  *    post : dopost()
  *  = destory() : �Ҵ�� �޸𸮸� ȸ���Ѵ�
  *  = ���ΰ�ħ, ȭ�� �̵� => destroy() ȣ�� => �޸� ȸ��  
  */
 // xml�� �����͸� �о map�� ���� (Controller�� ����ϰ� �ִٰ� ��û�� �ٷ� Ŭ������ ã�´�)
 // Callback�Լ���	
 // ���� (�� Ŭ���� ����)
	private Map clsMap=new HashMap();
	
	public void init(ServletConfig config) throws ServletException {
		
		// web.xml�� ��ϵ� applicationContext.xml������ �б�
		// web.xml�� ������ �����͸� �о���� Ŭ���� => SevletConfig
		String path=config.getInitParameter("contextConfigLocation");
		System.out.println(path);
		try {
		
			// xml�б�
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			// xml�� �Ľ��� �� �ְ� Ŭ���� ����
			
			DocumentBuilder db=dbf.newDocumentBuilder(); // �Ḻ̌�
			// xml, wml, hdml
			// xml�� �о �޸𸮿� ���� (������� : Document)
			Document doc=db.parse(new File(path));
			// �ֻ��� �±� => Ʈ�� ���·� ����
			Element root=doc.getDocumentElement();
			System.out.println(root.getTagName()); // <beans>
			
			// bean �±� �б�
			// bean �±׸� ��� ���
			// ���� �±׸� ���� �� ����ϴ� Ŭ���� => NodeList
			NodeList list=root.getElementsByTagName("bean");
			/*
			 *  <bean id="list.do" class="com.sist.model.ListModel"/>
 				<bean id="detail.do" class="com.sist.model.DetailModel"/>
 				<bean id="insert.do" class="com.sist.model.InsertModel"/>
			 * 
			 */
			for(int i=0; i<list.getLength(); i++) {
				
				// bean �б�
				Element bean=(Element)list.item(i);
				String cmd=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				System.out.println("cmd="+cmd+",class="+cls);	
				
				// ����
				// Ŭ������ �޸� �Ҵ��� �� �Ŀ� Ű, �ּ�
				Class clsName=Class.forName(cls);
				Object obj=clsName.newInstance(); // Ŭ���� �̸��� �о �޸� �Ҵ�
				System.out.println("�Ҵ�� �ּ�:"+obj);
				
				// ����(Map)
				clsMap.put(cmd, obj);
				
			}
			
		} catch (Exception e) {

		}
	}
 // ����� ��û ó���ϴ� �޼ҵ�
 // Callback �Լ���
 // Callback => ���α׷��Ӱ� ȣ���ϴ� �޼ҵ尡 �ƴ϶� �ý����� ���� �ڵ� ȣ��Ǵ� �޼ҵ�
 // main()	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ����� ��û�ÿ� ó���� �Ǵ� ����
		// http://localhost/OnlineStudy16_MVC1/list.do
		// 1. ����� ��û�� �޴´�
		String cmd=request.getRequestURI(); //OnlineStudy16_MVC1/list.do => URI
//											  ================== contextpath
		// 2. ModelŬ���� ã��
		cmd=cmd.substring(request.getContextPath().length()+1); // list.do, detail.do
		Model model=(Model)clsMap.get(cmd); // if�� ������� �ʾƵ� �ȴ�
		// 1, 2�� ó���ϱ� ���ؼ� ���õ� �����͸� Map�� ���� => clsMap�� ����� Ŭ������ ã�´�
		// 3. ModelŬ������ ���ؼ� ��ûó�� �Ϸ� => ������� request, session�� ��´�
		String jsp=model.handlerRequest(request); // ó�� �Ŀ� request�� ���� ����ش�
		// 4. jsp�� ã�´�
		// 5. jsp�� request�� �����Ѵ�
		// request�� �ش� jsp�� �����ϴ� Ŭ���� => RequestDispatcher
		if(jsp.startsWith("redirect")) {
			
			response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
			// ȭ���̵� => sendRedirect() => request�� �ʱ�ȭ
			
		} else {
			
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);
			// ȭ���̵� => forward => request�� ���� => jsp���� request�� ���� �����͸� ���
		}
	
	}

}
