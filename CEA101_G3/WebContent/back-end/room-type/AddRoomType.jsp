<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomtype.model.*"%>

<% 
	String insertSuccess = (String)request.getAttribute("insertSuccess");
	String repeat = (String)request.getAttribute("repeat");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:if test="${not empty errorMsgs}">
	<script>
		$("#tab-3").prop("checked",true);
		swal("新增失敗","請修正錯誤！","error");	
	</script>		
	</c:if>
	<c:if test="${repeat == 'repeat'}">
	<script>
		$("#tab-3").prop("checked",true);
		swal("編號重複","請修正錯誤！","error");
	</script>
	</c:if>
	<c:if test="${insertSuccess == 'yes' }">
		<script>
			$("#tab-3").prop("checked",true);
			swal("新增成功", "請查看房型", "success");
		</script>
	</c:if>
</head>
<body>
	<%-- 錯誤表列 --%>
<%-- 	<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 	</c:forEach> --%>
	
	<form method="post" action="${pageContext.request.contextPath}/RoomType.do">
		<div id="content-3">
			房型編號：<input class="input-beautify" type="text" name="room_category_id" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
				<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_category_id}</p>
			</c:if>
				
			房型名稱：<input class="input-beautify" type="text" name="room_name" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
				<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_name}</p>
			</c:if>
			
			房型類型：<input class="input-beautify" type="text" name="room_type" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
				<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_type}</p>
			</c:if>
				
			房型價格：<input class="input-beautify" type="text" name="room_price" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
				<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_price}</p>
			</c:if>
				
			房間坪數：<input class="input-beautify" type="text" name="area" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
				<p class="error" style="color:red; font-size:8px;">${errorMsgs.area}</p>
			</c:if>
			
			可住人數：<input class="input-beautify" type="text" name="room_guest" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
				<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_guest}</p>
			</c:if>
			
			房間數量：<input class="input-beautify" type="text" name="room_quantity" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
				<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_quantity}</p>
			</c:if>
					
			房型狀態：<input class="input-beautify" type="text" name="room_category_status" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
				<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_category_status}</p>
			</c:if>
					
			房型敘述：<input class="input-beautify" type="text" name="room_info" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
				<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_info}</p>
			</c:if>
			
			<input type="hidden" name="action" value="insert"><br>
			<button id="add" type="submit" class="btn btn-primary">新增房型</button>
		</div>
	</form>
</body>
</html>