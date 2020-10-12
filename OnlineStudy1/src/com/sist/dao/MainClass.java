package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 try
	        {
	        	Class.forName("oracle.jdbc.driver.OracleDriver");
	        	String url="jdbc:oracle:thin:@211.238.142.181:1521:XE";
	        	Connection conn=DriverManager.getConnection(url,"hr","happy");
	        	String sql="SELECT empno,ename,job FROM emp";
	        	PreparedStatement ps=conn.prepareStatement(sql);
	        	ResultSet rs=ps.executeQuery();
	        	while(rs.next())
	        	{
	        		System.out.println(rs.getInt(1)+" "
	        	      +rs.getString(2)+" "+rs.getString(3));
	        	}
	        	rs.close();
	        }catch(Exception ex) {System.out.println(ex.getMessage());}
		}

	}


