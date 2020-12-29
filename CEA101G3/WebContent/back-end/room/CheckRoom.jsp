<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomtype.model.*" %>
<%
	List<RoomVO> list = null;
	list = (List<RoomVO>)request.getAttribute("getRoomVoByRtc");
	if (list == null) {
		RoomService roomSvc = new RoomService();
		list = roomSvc.getAllRM();
	}
	pageContext.setAttribute("list", list);
	
	RoomTypeService roomTypeSvc = new RoomTypeService();
	List<RoomTypeVO> roomTypeList = roomTypeSvc.getAllRT();
	pageContext.setAttribute("roomTypeList", roomTypeList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

.queryRT{
	margin: 0;
	color: #f9f9f9;
	background: #16a085;
	border: none;
	width: 50px;
	height: 23px;
	margin-left: 2px;
	border-radius: 4px;
	border-bottom: 2px solid #117A60;
	transition: all .2s ease;
	outline: none;
}

.queryRT:hover {
	background: #149174;
	color: #0C5645;
}
.queryRT:active{
	border:0;
}
</style>
<c:if test="${not empty errorUpdateMsgs}">
	<script>
		swal("更新失敗","請重新修改！","error");	
	</script>		
</c:if>
<c:if test="${updateSuccess == 'yes' }">
		<script>
			swal("更新成功", "請查看房間", "success");
		</script>
	</c:if>
</head>
<body>
	<div id="content-1">
		<h2 style="text-align:center; margin-bottom:20px;">房間資訊</h2>
		<table id="myTable" border="1px solid #000">
			<tr class="header">
				<th>房間編號</th>
				<th style="width:162px;">房型編號</th>
				<th>房間狀態</th>
				<th>修改房間</th>
				<th>刪除房間</th>
			</tr>
			<div style="display:inline-block; width:600px; margin-bottom:8px;">	
				<div style="display:inline;">
					<form method="post" action="${pageContext.request.contextPath}/Room.do">
						<select name="room_category_id">
								<option>請選擇查詢房型</option>
							<c:forEach var="roomType" items="${roomTypeList}">
								<option value="${roomType.room_category_id}">${roomType.room_category_id}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="action" value="getRoomByRtc">
						<input class="queryRT" type="submit" value="查詢">
					</form>		
				</div>
				<%@ include file="page1.file"%>
			</div>
			<c:forEach var="roomVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			
			<tr>
				<td>${roomVO.room_id}</td>
				<td style="width:162px;">${roomVO.room_category_id}</td>			
				<td style="text-align:center;">
				<c:choose>
					<c:when test="${roomVO.status == '0'}">
						<i style="color:green;" class="fa fa-circle"></i>
					</c:when>
					<c:when test="${roomVO.status == '1'}">
						<i style="color:red;" class="fa fa-circle"></i>
					</c:when>
					<c:otherwise>
						<i style="color:orange;" class="fa fa-circle"></i>
					</c:otherwise>
				</c:choose></td>
				<td>	
			    <button class="edit btn btn-info" type="submit">修改</button>
				</td>
				<td>
				<form method="post" action="${pageContext.request.contextPath}/Room.do">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="room_id" value="${roomVO.room_id}">
				    <button class="delete btn btn-secondary" type="submit">刪除</button>
				</form>
				</td>
			</tr>
			</c:forEach>	
		</table>
		<%@ include file="page2.file"%>
		<div id="lightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/Room.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>房間修改</td></tr>
					<tr><td>房間編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_id" class="input-noEdit" type="text" name="room_id" readonly></td></tr>			
					<tr><td>房型編號：</td>
						<td>
						<select class="input-beautify" name="room_category_id" required>
							<option value="notChoice">請選擇房型</option>
						<c:forEach var="rtc" items="${roomTypeList}">
							<option value="${rtc.room_category_id}">${rtc.room_category_id}</option>
						</c:forEach>			
						</select>
						</td>
					</tr>
					<tr><td>房間狀態：</td>
						<td>
						<select class="input-beautify" name="status" required>
							<option value="99">請選擇狀態</option>
							<option value="0">可入住</option>
							<option value="1">修繕中</option>
							<option value="2">入住中</option>		
						</select>
						</td>
					</tr>		
					<tr><td colspan="2" align="center">				
					<input type="hidden" name="action" value="update">
					<input class="btn btn-info" type="submit" id="btnEdit" value="送出修改">
					<input class="btn btn-warning" type="button" id="btnEditCancel" value="取消">
				</table>
			</form>
		</div>
	</div>
	<script>		
		$(".edit").click(function() {
			$("#lightBox").css("display","");
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#room_id").val(children.eq(0).text());
		})
		
		$("#btnEditCancel").click(function() {
			$("#lightBox").css("display","none");
		})
	</script>	
</body>
</html>