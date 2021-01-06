<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.extra_charges.model.*"%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<%
	Extra_chargesService extra_chargesSvc = new Extra_chargesService();
	List<Extra_chargesVO> list = extra_chargesSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<jsp:useBean id="empSvc" scope="page" class="com.extra_charges.model.Extra_chargesService" />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content-1">
		<h2 style="text-align:center; margin-bottom:20px;">額外消費資訊</h2>
		<table id="myTable" border="1px solid #000">
			<tr class="header">
				<th>額外消費序號</th>
				<th>訂房訂單編號</th>
				<th>消費內容</th>
				<th>價格</th>
				<th>更新消費內容</th>
				<th>刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			
			<c:forEach var="extra_chargesVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">			
			<tr>
				<td>${extra_chargesVO.extra_charges_id}</td>
				<td>${extra_chargesVO.room_order_id}</td>
				<td>${extra_chargesVO.item}</td>
				<td>${extra_chargesVO.price}</td>
				
				
				<td>
				<input type="hidden" name="extra_charges_id" value="${extra_chargesVO.extra_charges_id}">
				<input type="hidden" name="action" value="getOne_For_Update">	
			    <button class="edit btn btn-info" type="submit">修改</button>
				</td>
				<td>
					<input type="hidden" name="extra_charges_id" value="">
				    <button class="delete btn btn-danger" type="submit">刪除</button>
				
				</td>
			</tr>
			</c:forEach>	
		</table>
			<%@ include file="page2.file"%>
		<div id="lightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/extra_charges/extra_charges.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>額外消費修改</td></tr>
					<tr><td>額外消費編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="extra_charges_id" class="input-beautify" type="text" name="extra_charges_id" readonly></td></tr>			
					<tr><td>訂房訂單編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_order_id" class="input-beautify" type="text" name="room_order_id" readonly ></td></tr>
					<tr><td>消費內容：</td><td><input style="background-color:#FFE1AB; border:none;" id="item" class="input-beautify" type="text" name="item" required></td></tr>
					<tr><td>價格：</td><td><input style="background-color:#FFE1AB; border:none;"id="price" class="input-beautify" type="text" name="price" required></td></tr>
					
					<td><input type="hidden" name="action" value="update">
					<input class="btn btn-info" type="submit" id="btnEdit" value="送出修改">
					<input class="btn btn-warning" type="button" id="btnEditCancel" value="取消">		
					</TD></TR>
				</table>
				
			</form>
		</div>
	</div>
	<script>		
		$(".edit").click(function() {
			$("#lightBox").css("display","");
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#extra_charges_id").val(children.eq(0).text());
			$("#room_order_id").val(children.eq(1).text());
			$("#item").val(children.eq(2).text());
			$("#price").val(children.eq(3).text());
		})
		
		$("#btnEditCancel").click(function() {
			$("#lightBox").css("display","none");
		})
		
		
		$(".delete").click(function() {
			window.alert("不可刪除");
			
		})
	</script>	

</body>

</html>