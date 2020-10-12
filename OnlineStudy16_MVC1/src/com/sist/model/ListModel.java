package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.dao.*;

public class ListModel implements Model{
// 게시판 목록 처리 =>
	
	@Override
	public String handlerRequest(HttpServletRequest request) {

		// 게시물 목록을 가지고 온다 => request에 값을 담아서 JSP로 전송
		String page=request.getParameter("page"); // 사용자가 요청한 페이지를 받는다
		if(page==null) // 게시판을 실행할 때 나타나는 첫 페이지
			page="1"; // default
		int curpage=Integer.parseInt(page);
		// 현재페이지 => list목록을 가지고 온다
		Map map=new HashMap();
		// WHERE num BETWEEN #{start} AND #{end}
		// start(시작위치, 끝위치) => Mybatis에서 처리
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1); // rownum은 1부터 시작
		int end=rowSize*curpage;
		
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list=BoardDAO.boardListData(map);
		
		int totalpage=BoardDAO.boardTotalPage();
		
		/*
		 * jsp로 전송해야할 데이터는 3개 (현재페이지, 총페이지, list목록)
		 */
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		return "board/list.jsp"; // 어떤 JSP로 request를 보낼것인지
	}

}
