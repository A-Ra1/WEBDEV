package com.sist.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/////// xml 파싱
import javax.xml.parsers.*;
import org.w3c.dom.*;
///////
import com.sist.model.*;
// 연결 (Model ==== JSP)
/*
 * Controller 등록 => web.xml
 */
import java.util.*;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
 /*
  *  서블릿 동작
  *  = 메모리 할당 (생성자 호출)
  *  = init() : 파일 읽기, 서버 연결
  *  = service() : 사용자가 요청시 처리하는 메소드
  *    ======== get / post => 동시에 처리가 가능한 메소드
  *    get : doget()
  *    post : dopost()
  *  = destory() : 할당된 메모리를 회수한다
  *  = 새로고침, 화면 이동 => destroy() 호출 => 메모리 회수  
  */
 // xml의 데이터를 읽어서 map에 저장 (Controller가 기억하고 있다가 요청시 바로 클래스를 찾는다)
 // Callback함수다	
 // 저장 (모델 클래스 저장)
	private Map clsMap=new HashMap();
	
	public void init(ServletConfig config) throws ServletException {
		
		// web.xml에 등록된 applicationContext.xml파일을 읽기
		// web.xml에 설정된 데이터를 읽어오는 클래스 => SevletConfig
		String path=config.getInitParameter("contextConfigLocation");
		System.out.println(path);
		try {
		
			// xml읽기
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			// xml을 파싱할 수 있게 클래스 생성
			
			DocumentBuilder db=dbf.newDocumentBuilder(); // 파싱기
			// xml, wml, hdml
			// xml을 읽어서 메모리에 저장 (저장공간 : Document)
			Document doc=db.parse(new File(path));
			// 최상위 태그 => 트리 형태로 저장
			Element root=doc.getDocumentElement();
			System.out.println(root.getTagName()); // <beans>
			
			// bean 태그 읽기
			// bean 태그를 묶어서 사용
			// 같은 태그를 묶을 때 사용하는 클래스 => NodeList
			NodeList list=root.getElementsByTagName("bean");
			/*
			 *  <bean id="list.do" class="com.sist.model.ListModel"/>
 				<bean id="detail.do" class="com.sist.model.DetailModel"/>
 				<bean id="insert.do" class="com.sist.model.InsertModel"/>
			 * 
			 */
			for(int i=0; i<list.getLength(); i++) {
				
				// bean 읽기
				Element bean=(Element)list.item(i);
				String cmd=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				System.out.println("cmd="+cmd+",class="+cls);	
				
				// 저장
				// 클래스를 메모리 할당을 한 후에 키, 주소
				Class clsName=Class.forName(cls);
				Object obj=clsName.newInstance(); // 클래스 이름을 읽어서 메모리 할당
				System.out.println("할당된 주소:"+obj);
				
				// 저장(Map)
				clsMap.put(cmd, obj);
				
			}
			
		} catch (Exception e) {

		}
	}
 // 사용자 요청 처리하는 메소드
 // Callback 함수다
 // Callback => 프로그래머가 호출하는 메소드가 아니라 시스템을 위해 자동 호출되는 메소드
 // main()	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 요청시에 처리가 되는 영역
		// http://localhost/OnlineStudy16_MVC1/list.do
		// 1. 사용자 요청을 받는다
		String cmd=request.getRequestURI(); //OnlineStudy16_MVC1/list.do => URI
//											  ================== contextpath
		// 2. Model클래스 찾기
		cmd=cmd.substring(request.getContextPath().length()+1); // list.do, detail.do
		Model model=(Model)clsMap.get(cmd); // if를 사용하지 않아도 된다
		// 1, 2를 처리하기 위해서 관련된 데이터를 Map에 저장 => clsMap에 저장된 클래스를 찾는다
		// 3. Model클래스를 통해서 요청처리 완료 => 결과값을 request, session에 담는다
		String jsp=model.handlerRequest(request); // 처리 후에 request에 값을 담아준다
		// 4. jsp를 찾는다
		// 5. jsp로 request를 전송한다
		// request를 해당 jsp로 전송하는 클래스 => RequestDispatcher
		if(jsp.startsWith("redirect")) {
			
			response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
			// 화면이동 => sendRedirect() => request가 초기화
			
		} else {
			
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);
			// 화면이동 => forward => request를 전송 => jsp에서 request에 담은 데이터를 출력
		}
	
	}

}
