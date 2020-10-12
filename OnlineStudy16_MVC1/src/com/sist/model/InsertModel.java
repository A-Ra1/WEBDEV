package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class InsertModel implements Model{
// 글쓰기 요청시 처리	

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "board/insert.jsp"; // 화면만 이동
	}

}
