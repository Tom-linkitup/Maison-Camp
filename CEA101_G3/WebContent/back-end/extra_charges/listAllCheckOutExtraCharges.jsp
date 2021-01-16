<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.extra_charges.model.*"%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<%
	Extra_chargesService extra_chargesSvc = new Extra_chargesService();
	List<Extra_chargesVO> list = extra_chargesSvc.getAllCheckOut();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<jsp:useBean id="empSvc" scope="page" class="com.extra_charges.model.Extra_chargesService" />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content-3">
		<h2 style="text-align:center; margin-bottom:20px;">額外消費資訊(已退房)</h2>
		<table id="myTable" border="1px solid #000">
			<tr class="header">
				<th>額外消費序號</th>
				<th>訂房訂單編號</th>
				<th>消費內容</th>
				<th>價格</th>
				
			</tr>
			<%@ include file="page1.file"%>
			
			<c:forEach var="extra_chargesVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">			
			<tr>
				<td>${extra_chargesVO.extra_charges_id}</td>
				<td>${extra_chargesVO.room_order_id}</td>
				<td>${extra_chargesVO.item}</td>
				<td>${extra_chargesVO.price}</td>
			</tr>
			</c:forEach>	
		</table>
			<%@ include file="page2.file"%>
	</div>

</body>

</html>