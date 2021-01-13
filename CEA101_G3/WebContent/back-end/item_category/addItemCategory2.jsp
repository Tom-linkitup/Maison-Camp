<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_category.model.*"%>

<% 
ItemCategoryVO itemCategoryVO = (ItemCategoryVO) request.getAttribute("remain");
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
			swal("新增成功", "請查看商品類別", "success");
		</script>
	</c:if>
</head>
<body>
	
	<form method="post" action="<%=request.getContextPath()%>/item_category/itemCategory.do">
		<div id="content-2">
		
			商品類別編號：<input class="input-beautify" type="text" name="itemCategoryId" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.itemCategoryId}</p>
			</c:if>
				
			商品類別名稱：<input class="input-beautify" type="text" name="itemCategoryName" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.itemCategoryName}</p>
			</c:if>
			
			<input type="hidden" name="action" value="insert"><br>
			<button id="add" type="submit" class="btn btn-primary">新增商品</button>
		</div>
	</form>
</body>
</html>