<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomtype.model.*"%>

<% 
	RoomTypeService roomTypeSvc = new RoomTypeService();
	List<RoomTypeVO> list = roomTypeSvc.getAllRT();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:if test="${not empty errorMsgs}">
	<script>
		$("#tab-2").prop("checked",true);
		swal("新增失敗","請修正錯誤！","error");	
	</script>
			
	</c:if>
	<c:if test="${insertSuccess == 'yes' }">
		<script>
			$("#tab-2").prop("checked",true);
			swal("新增成功", "請查看房間", "success");
		</script>
	</c:if>
</head>
<body>
	<%-- 錯誤表列 --%>
	<c:forEach var="message" items="${errorMsgs}">
	</c:forEach>
	
	<form method="post" action="${pageContext.request.contextPath}/Room.do">
		<div id="content-2">
			<span>選擇房型：</span>
			<select class="input-beautify" name="room_category_id">
					<option value="notChoice">請選擇</option>
				<c:forEach var="room" items="${list}">
					<option value="${room.room_category_id}">${room.room_category_id}</option>
				</c:forEach>
			</select>	
			<%-- <p style="font-size:8px; color:red;">${errorMsgs.room_category_id}</p> --%>
			
			<span>房間狀態：</span>
			<select class="input-beautify" name="status">
				<option value="99">請選擇</option>
				<option value="0">可入住</option>
				<option value="1">修繕中</option>
				<option value="2">in入住中</option>
			</select>	
			<%-- <p style="font-size:8px; color:red;">${errorMsgs.status}</p> --%>	
			<input type="hidden" name="action" value="insert"><br>
			<button id="add" type="submit" class="btn btn-primary">新增房間</button>
		</div>
	</form>
</body>
</html>