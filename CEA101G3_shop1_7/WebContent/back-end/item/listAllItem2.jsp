<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_photo.model.*" %>
<%@ page import="com.item_category.model.*"%>
<%
	ItemService itemSvc = new ItemService();
	List<ItemVO> list = itemSvc.getAll();
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
		<h2 style="text-align:center; margin-bottom:20px;">房型資訊</h2>
		<table id="myTable" border="1px solid #000">
			<tr class="header">
				<th>商品編號</th>
				<th>商品類別編號</th>
				<th>商品名稱</th>
				<th>商品資訊</th>
				<th>商品價格</th>
				<th>商品狀態</th>
				<th>商品修改</th>
				<th>商品刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="itemVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">			
			<tr>
				<td>${itemVO.itemId}</td>
				<td>${itemVO.itemCategoryId}</td>
				<td>${itemVO.itemName}</td>
				<td>${itemVO.itemInfo}</td>
				<td>${itemVO.itemPrice}</td>
				<td><c:choose>
					<c:when test="${itemVO.itemStatus == '0'}">
						上架
					</c:when>
					<c:otherwise>
						下架
					</c:otherwise>
					</c:choose></td>
				<td>
				<input type="hidden" name="itemId" value="${itemVO.itemId}">
				<input type="hidden" name="action" value="getOne_For_Update">	
			    <button class="edit btn btn-info" type="submit">修改</button>
				</td>
				<td>
				<form method="post" action="<%=request.getContextPath()%>/item/item.do">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="itemId" value="${itemVO.itemId}">
				    <button class="delete btn btn-danger" type="submit">刪除</button>
				</form>
				</td>
			</tr>
			</c:forEach>	
		</table>
		<%@ include file="page2.file"%>
		<div id="lightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/item/item.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>商品修改</td></tr>
					<tr><td>商品編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="itemId" class="input-noEdit" type="text" name="itemId" readonly></td></tr>			
					<tr><td>商品類別編號：</td><td><input id="itemCtaegoryId" class="input-beautify" type="text" name="itemCtaegoryId" required></td></tr>
					<tr><td>商品名稱：</td><td><input id="itemName" class="input-beautify" type="text" name="itemName" required></td></tr>
					<tr><td>商品資訊：</td><td><input id="itemInfo" class="input-beautify" type="text" name="itemInfo" required></td></tr>
					<tr><td>商品價格：</td><td><input id="itemPrice" class="input-beautify" type="number" name="itemPrice" required></td></tr>
					<tr><td>商品狀態：</td>
						<td>
						<select id="itemStatus" class="input-beautify" type="number" name="itemStatus" required>
							<option>請選擇狀態</option>
							<option value="0">上架</option>
							<option value="1">下架</option>
						</select>
						</td>
					</tr>			
					<tr><td colspan="2" align="center">
					
					<input type="hidden" name="action" value="update">
					
					<input class="btn btn-info" type="submit" id="itemId" value="送出修改">
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
			$("#itemId").val(children.eq(0).text());
			$("#itemCtaegoryId").val(children.eq(1).text());
			$("#itemName").val(children.eq(2).text());
			$("#itemInfo").val(children.eq(3).text());
			$("#itemPrice").val(children.eq(4).text());
			$("#itemStatus").val("請選擇狀態");
		})
		
		$("#btnEditCancel").click(function() {
			$("#lightBox").css("display","none");
		})
	</script>	
</body>
</html>