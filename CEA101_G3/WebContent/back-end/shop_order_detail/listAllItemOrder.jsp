<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.shop_order.model.*" %>
<%@ page import="com.item_category.model.*"%>
<%
ShopOrderService ShopOrderSvc = new ShopOrderService();
	List<ShopOrderVO> list = ShopOrderSvc.getAll();
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
				<th>訂單編號</th>
				<th>會員編號</th>
				<th>付款</th>
				<th>時間</th>
				<th>總金額</th>
				<th>商品狀態</th>
				<th>狀態修改</th>
				<th></th>
				</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="ShopOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">			
			<tr>
				<td>${ShopOrderVO.shop_order_id}</td>
				<td>${ShopOrderVO.mem_id}</td>
				<td>${ShopOrderVO.payment}</td>
				<td>${ShopOrderVO.time}</td>
				<td>${ShopOrderVO.shop_total_amount}</td>
				<td><c:choose>
					<c:when test="${ShopOrderVO.status == '1'}">
						待出貨
					</c:when>
					<c:otherwise>
						已完成
					</c:otherwise>
					</c:choose></td>
				<td>
				<input type="hidden" name="itemId" value="${ShopOrderVO.shop_order_id}">
				<input type="hidden" name="action" value="getOne_For_Update">	
			    <button class="edit btn btn-info" type="submit">修改</button>
				</td>
				<td>
					<form method="post" action="${pageContext.request.contextPath}/shop_order/shop_order.do">
						<input type="hidden" name="shop_order_id" value="${ShopOrderVO.shop_order_id}">
						<input type="hidden" name="mem_id" value="${ShopOrderVO.mem_id}">
						<input type="hidden" name="payment" value="${ShopOrderVO.payment}">
						<input type="hidden" name="time" value="${ShopOrderVO.time}">
						<input type="hidden" name="shop_total_amount" value="${ShopOrderVO.shop_total_amount}">
						<input type="hidden" name="status" value="2">	
						<input type="hidden" name="action" value="update">			
						<button class="btn btn-info" type="submit">完成訂單</button>
					</form>
				</td>
			</tr>
			</c:forEach>	
		</table>
		<%@ include file="page2.file"%>
		<div id="lightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/shop_order/shop_order.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>商品修改</td></tr>
					<tr><td>訂單編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="shop_order_id" class="input-noEdit" type="text" name="shop_order_id" readonly></td></tr>			
					<tr><td>商品類別編號：</td><td><input id="mem_id" class="input-beautify" type="text" name="mem_id" required readonly></td></tr>
					<tr><td>商品名稱：</td><td><input id="payment" class="input-beautify" type="text" name="payment" required readonly></td></tr>
					<tr><td>商品資訊：</td><td><input id="time" class="input-beautify" type="text" name="time" required readonly></td></tr>
					<tr><td>商品價格：</td><td><input id="shop_total_amount" class="input-beautify" type="text" name="shop_total_amount" required readonly></td></tr>
					<tr><td>訂單狀態：</td>
						<td>
						<select id="status" class="input-beautify" type="number" name="status" required>
							<option value="1">未出貨</option>
							<option value="2">已完成</option>
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
			$("#shop_order_id").val(children.eq(0).text());
			$("#mem_id").val(children.eq(1).text());
			$("#payment").val(children.eq(2).text());
			$("#time").val(children.eq(3).text());
			$("#shop_total_amount").val(children.eq(4).text());
			$("#itemStatus").val("請選擇狀態");
		})
		
		$("#btnEditCancel").click(function() {
			$("#lightBox").css("display","none");
		})
	</script>	
	
</body>
</html>