package com.sist.model;

import javax.servlet.http.HttpServletRequest;
/*
 * list.jsp => HTML�� ��� (�����Ͱ� ���� ����)
 * list.do => �����͸� list.jsp�� �����Ŀ� ���
 * 
 */
public class InsertOkModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "redirect:list.do"; // �̵� => ��� => list.do
	}

}
