package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class InsertModel implements Model{
// �۾��� ��û�� ó��	

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "board/insert.jsp"; // ȭ�鸸 �̵�
	}

}
