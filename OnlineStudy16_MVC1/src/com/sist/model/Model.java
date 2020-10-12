package com.sist.model;

import javax.servlet.http.HttpServletRequest;

/*
 *  Interface : 
 *  ���� �ٸ� �������� Ŭ������ ��� �Ѱ��� �̸� (�������̽�)���� ���� or ����
 *  ���
 *   extends, implement
 *  
 *  ���� : �������̽��� �����ϴ� �޼ҵ忡 ���� => �������̽��� �޼ҵ带 �����ϸ� �����ϰ� �ִ� ��� Ŭ������ error�߻�
 *  POJO (�������̽� ����(X), ���(X)) => ���� (������̼�) => Spring 
 * 
 */
public interface Model {
	
	// ��� (��û) ó���ϴ� �޼ҵ� => Model�� ��� Ŭ������ ��û�� ó���ϱ� ���� �޼ҵ�
	public String handlerRequest(HttpServletRequest request);
	// handlerRequest => Spring���� �����ϴ� �޼ҵ�
	// Call By Request => �ּ�(�޸�)�� �Ѱ��ְ� ���� �޸𸮿� ä���ִ� ��� (Ŭ�����϶�, �迭�϶�)
	/*
	 * �Ű����� ���۹�� : Call by Value : �ٸ� �޸𸮿� ���� ���� (�Ϲ� ������)
	 * 			      Call by Reference : ���� �޸� �ּ� (������ �Ѿ��)
	 * 
	 * MVC ���� ����
	 *   ���������� ��û�� �޴� ��� (JSP/Servlet)
	 * ����� == ��Ʈ�ѷ� == ModelŬ���� == �����ͺ��̽�(DAO) == ModelŬ���� == ��Ʈ�ѷ� == JSP�� ���� == �����   
	 * 
	 * 1) ��Ʈ�ѷ� : ���� (�𵨰� �並 ����)
	 * 2) �� : ��ûó�� => Ȯ��(����) �Ϲ� �ڹ�
	 * 3) �� : ��û ó���� ������� ��� => JSP (EL, JSTL)
	 * 
	 * ��Ʈ�ѷ� => �����ؾߵǴ� �𵨰� �並 �˰� �ִ�
	 * 1) ��û�� �޴´�
	 *  String cmd=request.getRequestURI()
	 *  => (list).do
	 * 2) ���� ã�´� => ListModel
	 * 3) �𵨿��� �Ѱ��� ������� request, session�� ��Ƽ�
	 *   request.setAttribute()
	 * 4) jsp�� ã�� => request, session�� �Ѱ��ش�
	 *   forward(requst, response)
	 *   
	 *   list.do => ListModel => list.jsp
	 *   detail.do => DetailModel => detail.jsp : Map�� �̿��ؼ� (��û: Ű, �ش� �ڹ�����)
	 *   
	 *  *** Controller�� �����ϱ� ���ؼ� Controller �ҽ� ���� => ��ü ����Ʈ�� ������ �� ����
	 *   => ������ �̿��Ѵ� (.properties, .xml)
	 *   							  ==== Spring, structure 
	 */

}
