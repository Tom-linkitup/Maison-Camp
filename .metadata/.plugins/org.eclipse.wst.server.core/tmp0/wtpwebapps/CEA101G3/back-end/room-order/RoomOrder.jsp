<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.roomorder.model.*" %>

<%
	RoomOrderService roomOrderSvc = new RoomOrderService();
	List<RoomOrderVO> list = roomOrderSvc.getAllRO();
	RoomOrderVO room = roomOrderSvc.getOneRO("RD7");
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("room", room);
%>
<!DOCTYPE html>
<%@ include file="backIndex.file"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/room-order.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<header>
		<h2 style="text-align:center; margin-bottom:20px;">訂房訂單資訊</h2>
	</header>
	<div>
		<table id="myTable" class="table table-striped" style="text-align:center;">
			<tr>
				<th>訂單編號</th>
				<th>會員編號</th>
				<th>訂單創建日期</th>
				<th>訂單金額</th>
				<th>訂單狀態</th>
			</tr>
			<c:forEach var="roomOrderVO" items="${list}">
			<tr>	
				<td>${roomOrderVO.room_order_id}</td>
				<td>${roomOrderVO.mem_id}</td>
				<td>${roomOrderVO.time}</td>
				<td>${roomOrderVO.room_total_amount}</td>
				<td>${roomOrderVO.status}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
<%@ include file="backIndex2.file"%>
</body>
</html>