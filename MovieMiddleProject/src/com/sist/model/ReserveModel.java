package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.*;

import com.sist.controller.RequestMapping;
import com.sist.dao.MovieDAO;
import com.sist.vo.MovieVO;

public class ReserveModel {
  @RequestMapping("reserve/reserve.do")
  public String reserve_main(HttpServletRequest request)
  {
	  request.setAttribute("main_jsp", "../reserve/reserve.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("reserve/date.do")
  public String reserve_date(HttpServletRequest request) {
	  
	  String strYear=request.getParameter("year");
	  String strMonth=request.getParameter("month");
	  Date date=new Date();
	  
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
	  // MM dd (X) => 두자리를 반드시 채워야 할 때
	  String today=sdf.format(date);
	  StringTokenizer st=new StringTokenizer(today,"-");
	  
	  if(strYear==null) {
		  strYear=st.nextToken(); // yyyy 잘라오기
	  }
	  
	  if(strMonth==null) {
		  strMonth=st.nextToken();
	  }
	  
	  int day=Integer.parseInt(st.nextToken()); // 오늘날짜표시
	  int year=Integer.parseInt(strYear);
	  int month=Integer.parseInt(strMonth);
	  
	  Calendar cal=Calendar.getInstance(); // 추상클래스 
	  cal.set(Calendar.YEAR, year);
	  cal.set(Calendar.MONTH, month-1); // 배열
	  cal.set(Calendar.DATE, 1);
	  
	  // 요일을 구한다
	  int week=cal.get(Calendar.DAY_OF_WEEK); // 1~7 까지 들어온다
	  int lastday=cal.getMaximum(Calendar.DATE);
	  String[] strWeek= {"일","월","화","수","목","금","토"};
	  System.out.println("요일:"+strWeek[week-1]); // 1부터 시작하기때문에 실제 요일의 다음날이 출력된다
	  System.out.println("마지막날:"+lastday);
	  
	  // jsp로 전송
	  request.setAttribute("year", year);
	  request.setAttribute("month", month);
	  request.setAttribute("day", day);
	  request.setAttribute("week", week-1);
	  request.setAttribute("strWeek", strWeek);
	  request.setAttribute("lastday", lastday);
	  
	  return "../reserve/date.jsp"; // include 시키는 부분 X
  }
  
     @RequestMapping("reserve/movie.do")
     public String reserve_movie(HttpServletRequest request) {
    	 
    	 List<MovieVO> list=MovieDAO.movieReserveData();
    	 request.setAttribute("list", list);
    	 
    	 return "../reserve/movie.jsp";
    	 
     }
     
     // 극장
     // 시간
     // 인원
     // 예매
     // 마이페이지
     // 어드민 페이지
}