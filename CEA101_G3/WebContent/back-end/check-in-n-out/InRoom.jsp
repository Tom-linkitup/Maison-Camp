<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDate"%>

<%
	RoomOrderService roSvc = new RoomOrderService();
	List<RoomOrderVO> checkeds = roSvc.getOrderByStatus(new Integer(2)); //取得已入住訂單
	pageContext.setAttribute("checkeds", checkeds);
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="rodSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />
<jsp:useBean id="rtSvc" scope="page" class="com.roomtype.model.RoomTypeService" />
<jsp:useBean id="rmSvc" scope="page" class="com.room.model.RoomService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content-3" style="width:100%;">
		<table class="table table-striped" style="text-align:center;">
			<tr class="table-head">
				<th>訂單編號</th>
				<th>會員編號</th>
				<th>入住日期</th>
				<th>退房日期</th>
				<th>入住房號</th>
			</tr>
			<c:if test="${checkeds.size()==0}">
				<tr>
					<td colspan="7" class="td-msg">無入住中房客</td>
				</tr>
			</c:if>
			<c:forEach var="checked" items="${checkeds}">
				<tr class="list-data">
					<td>${checked.room_order_id}</td>
					<td>${memSvc.getOneMEM(checked.mem_id).name}</td>
					<td>${checked.check_in_date}</td>
					<td>${checked.check_out_date}</td>
					<td>${checked.current_room_id}</td>
				</tr>
			</c:forEach>		
		</table>
	</div>
</body>
</html>