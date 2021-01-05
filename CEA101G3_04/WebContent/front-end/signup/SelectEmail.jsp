<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String connUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection conn = DriverManager.getConnection(connUrl, "CEA101G3", "123456");
	
	String qry = "SELECT EMAIL FROM MEMBER ORDER BY MEM_ID";
	PreparedStatement pstmt2 = conn.prepareStatement(qry);
	
	ResultSet rs = pstmt2.executeQuery();
	
	String str = "";
	while(rs.next()){
		str += (str == "") ? rs.getString("EMAIL") : "," + rs.getString("EMAIL");
	}
	out.print(str);
	conn.close();
%>