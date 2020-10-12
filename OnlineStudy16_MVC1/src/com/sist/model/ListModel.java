package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.dao.*;

public class ListModel implements Model{
// �Խ��� ��� ó�� =>
	
	@Override
	public String handlerRequest(HttpServletRequest request) {

		// �Խù� ����� ������ �´� => request�� ���� ��Ƽ� JSP�� ����
		String page=request.getParameter("page"); // ����ڰ� ��û�� �������� �޴´�
		if(page==null) // �Խ����� ������ �� ��Ÿ���� ù ������
			page="1"; // default
		int curpage=Integer.parseInt(page);
		// ���������� => list����� ������ �´�
		Map map=new HashMap();
		// WHERE num BETWEEN #{start} AND #{end}
		// start(������ġ, ����ġ) => Mybatis���� ó��
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1); // rownum�� 1���� ����
		int end=rowSize*curpage;
		
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list=BoardDAO.boardListData(map);
		
		int totalpage=BoardDAO.boardTotalPage();
		
		/*
		 * jsp�� �����ؾ��� �����ʹ� 3�� (����������, ��������, list���)
		 */
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		return "board/list.jsp"; // � JSP�� request�� ����������
	}

}
