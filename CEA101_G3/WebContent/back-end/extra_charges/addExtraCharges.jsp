<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.extra_charges.model.*"%>

<% 
	Extra_chargesVO extra_chargesVO = (Extra_chargesVO) request.getAttribute("remain");
	String insertSuccess = (String)request.getAttribute("insertSuccess");
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

<c:if test="${repeat == 'repeat'}">
	<script>
		$("#tab-2").prop("checked",true);
		swal("編號重複","請修正錯誤！","error");
	</script>
	</c:if>
	
	<c:if test="${insertSuccess == 'yes' }">
		<script>
			$("#tab-2").prop("checked",true);
			swal("新增成功", "請查看額外消費編號", "success");
		</script>
	</c:if>
</head>
<body>

	<%-- 錯誤表列 --%>
	<c:forEach var="message" items="${errorMsgs}">
	</c:forEach>
<form method="post" action="${pageContext.request.contextPath}/extra_charges/extra_charges.do">	
<div id="content-2">
		<tr><td>訂房訂單編號:</td>
		<td><input type="TEXT" name="room_order_id" class="input-beautify" value="<%=(extra_chargesVO==null)? "" : extra_chargesVO.getRoom_order_id()%>"></td>
		<p></p>
		<c:if test="${not empty errorMsgs}">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_order_id}</p>
		</c:if>
		</tr>
		
		<tr><td>額外消費內容:</td>
		<td><input type="TEXT" name="item" class="input-beautify" value="" ></td>
		<p></p>
		<c:if test="${not empty errorMsgs}">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.item}</p>
		</c:if>
		</tr>
		
		<tr><td>消費金額總計:</td>
		<td><input type="TEXT" name="price" class="input-beautify" value=""></td>
		<p></p>
		<c:if test="${not empty errorMsgs}">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.price}</p>
		</c:if>
		</tr>

			<td><input type="hidden" name="action" value="insert"><br>
			<button id="add" type="submit" class="btn btn-primary">新增額外消費</button>
				
				</td>
				</tr>
		</div>
	</form>
</body>
</html>