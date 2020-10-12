package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;

public class DetailModel implements Model{
// �Խ��� �󼼺��� ó��	

	@Override
	public String handlerRequest(HttpServletRequest request) {
		
		// detail.do?no=1
		// request.setParameter("no", 1); => request.getParameter("no") ==> 1
		// ����ڰ� ���� ���� request�� ��� �Ѱ��ִ� ���� (Tomcat)
		// service (HttpServletRequest request) => request�� �Ű����� => ȭ���� �ٲ𶧸��� request�� �ʱ�ȭ
		// 1. ����ڰ� ������ �Խù� ��ȣ�� �޴´�
		String no=request.getParameter("no");
		
		// 2. BoardDAO�� ���ؼ� �Խù� �Ѱ��� �о� �´� (BoardVO) => SQL ���� ���� => board-mapper.xml
		BoardVO vo=BoardDAO.boardDetailData(Integer.parseInt(no));
		// 3. �о�� BoardVO���� jsp�� ����
		
		request.setAttribute("vo", vo);
		return "board/detail.jsp";
	}

}
