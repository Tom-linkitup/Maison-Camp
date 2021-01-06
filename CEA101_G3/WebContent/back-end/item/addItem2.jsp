<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>

<% 
	ItemVO itemVO = (ItemVO) request.getAttribute("remain");
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
			swal("新增成功", "請查看商品", "success");
		</script>
	</c:if>
</head>
<body>
	
	<form method="post" action="<%=request.getContextPath()%>/item/item.do">
		<div id="content-3">
				
			商品名稱：<input class="input-beautify" type="text" name="itemName" value="">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.itemName}</p>
				
			商品資訊：<input class="input-beautify" type="text" name="itemInfo" value="">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.itemInfo}</p>
				
			商品價格：<input class="input-beautify" type="text" name="itemPrice" value="">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.itemPrice}</p>
				
			商品狀態：<input class="input-beautify" type="text" name="itemStatus" value="">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.itemStatus}</p>
				
			<jsp:useBean id="ItemCategorySvc" scope="page" class="com.item_category.model.ItemCategoryService" />
			商品類別：<select size="1" name="itemCategoryId">
				<c:forEach var="itemCategoryVO" items="${ItemCategorySvc.all}">
				<option value="${itemCategoryVO.itemCategoryId}" ${(itemVO.itemCategoryId==itemCategoryVO.itemCategoryId)? 'selected':'' } >${itemCategoryVO.itemCategoryName}
				</c:forEach>
				</select>
			<p class="error" style="color:red; font-size:8px;"></p>
			
			<input type="hidden" name="action" value="insert"><br>
			<button id="add" type="submit" class="btn btn-primary">新增商品</button>
		</div>
	</form>
</body>
</html>