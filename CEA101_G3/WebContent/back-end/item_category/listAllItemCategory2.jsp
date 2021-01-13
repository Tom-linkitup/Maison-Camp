<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_photo.model.*" %>
<%@ page import="com.item_category.model.*"%>
<%
	ItemCategoryService itemCategorySvc = new ItemCategoryService();
	List<ItemCategoryVO> list = itemCategorySvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content-1">
		<h2 style="text-align:center; margin-bottom:20px;">商品資訊</h2>
		<table id="myTable" border="1px solid #000">
			<tr class="header">
				
				<th>商品類別編號</th>
				<th>商品類別名稱</th>
				<th>商品修改</th>
				<th>商品刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="itemCategoryVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">			
			<tr>
				<td>${itemCategoryVO.itemCategoryId}</td>
				<td>${itemCategoryVO.itemCategoryName}</td>
				<td>
				<input type="hidden" name="itemCategoryId" value="${itemCategoryVO.itemCategoryId}">
				<input type="hidden" name="action" value="getOne_For_Update">	
			    <button class="edit btn btn-info" type="submit">修改</button>
				</td>
				<td>
				<form method="post" action="<%=request.getContextPath()%>/item_category/itemCategory.do">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="itemCategoryId" value="${itemCategoryVO.itemCategoryId}">
				    <button class="delete btn btn-danger" type="submit">刪除</button>
				</form>
				</td>
			</tr>
			</c:forEach>	
		</table>
		<%@ include file="page2.file"%>
		<div id="lightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/item_category/itemCategory.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>商品修改</td></tr>
					<tr><td>商品類別編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="itemCategoryId" class="input-noEdit" type="text" name="itemCategoryId" readonly></td></tr>			
					<tr><td>商品類別名稱：</td><td><input id="itemCategoryName" class="input-beautify" type="text" name="itemCategoryName" required></td></tr>
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
			$("#itemCategoryId").val(children.eq(0).text());
			$("#itemCategoryName").val(children.eq(1).text());
		})
		
		$("#btnEditCancel").click(function() {
			$("#lightBox").css("display","none");
		})
	</script>	
</body>
</html>