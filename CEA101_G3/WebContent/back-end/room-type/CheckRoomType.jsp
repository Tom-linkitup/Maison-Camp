<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="com.roomphoto.model.*" %>
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
<style>
	.selectColor:hover {
		background-color:#ffbc00 !important;
	}
</style>
</head>
<body>
	<div id="content-1" style="width:100%;">
		<h2 style="text-align:center; margin-bottom:20px;">房型資訊</h2>
		<table class="table table-striped" id="myTable" style="width:100%;">
			<tr class="header">
				<th>編號</th>
				<th>名稱</th>
				<th>類型</th>
				<th>價格</th>
				<th>坪數</th>
				<th>可住人數</th>
				<th>數量</th>
				<th>狀態</th>
				<th>訊息</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			<c:forEach var="roomTypeVO" items="${list}">			
			<tr class="selectColor">
				<td>${roomTypeVO.room_category_id}</td>
				<td>${roomTypeVO.room_name}</td>
				<td>${roomTypeVO.room_type}</td>
				<td>${roomTypeVO.room_price}</td>
				<td>${roomTypeVO.area}</td>
				<td>${roomTypeVO.room_guest}</td>
				<td>${roomTypeVO.room_quantity}</td>
				<td><c:choose>
					<c:when test="${roomTypeVO.room_category_status == '0'}">
						上架
					</c:when>
					<c:otherwise>
						下架
					</c:otherwise>
					</c:choose></td>
				
				<td>${roomTypeVO.room_info}</td>
				<td>
				<input type="hidden" name="room_category_id" value="${roomTypeVO.room_category_id}">
				<input type="hidden" name="action" value="getOne_For_Update">	
			    <button class="edit btn btn-info" type="submit">修改</button>
				</td>
				<td>
				<form method="post" action="${pageContext.request.contextPath}/RoomType.do">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="room_category_id" value="${roomTypeVO.room_category_id}">
				    <button class="delete btn btn-danger" type="submit">刪除</button>
				</form>
				</td>
			</tr>
			</c:forEach>	
		</table>
		<div id="lightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/RoomType.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>房型修改</td></tr>
					<tr><td>房型編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_category_id" class="input-noEdit" type="text" name="room_category_id" readonly></td></tr>			
					<tr><td>房型名稱：</td><td><input id="room_name" class="input-beautify" type="text" name="room_name" required></td></tr>
					<tr><td>房型類型：</td><td><input id="room_type" class="input-beautify" type="text" name="room_type" required></td></tr>
					<tr><td>房型價格：</td><td><input id="room_price" class="input-beautify" type="number" name="room_price" required></td></tr>
					<tr><td>房型坪數：</td><td><input id="area" class="input-beautify" type="number" name="area" required></td></tr>
					<tr><td>可住人數：</td><td><input id="room_guest" class="input-beautify" type="number" name="room_guest" required></td></tr>
					<tr><td>房型數量：</td><td><input id="room_quantity" class="input-beautify" type="number" name="room_quantity" required></td></tr>
					<tr><td>房型狀態：</td>
						<td>
						<select id="room_category_status" class="input-beautify" type="text" name="room_category_status" required>
							<option value="0" selected>上架</option>
							<option value="1">下架</option>
						</select>
						</td>
					</tr>
					<tr><td>房型訊息：</td><td><input id="room_info" class="input-beautify" type="text" name="room_info" required></td></tr>			
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
			$("#room_category_id").val(children.eq(0).text());
			$("#room_name").val(children.eq(1).text());
			$("#room_type").val(children.eq(2).text());
			$("#room_price").val(children.eq(3).text());
			$("#area").val(children.eq(4).text());
			$("#room_guest").val(children.eq(5).text());
			$("#room_quantity").val(children.eq(6).text());
			$("#room_info").val(children.eq(8).text());
		})
		
		$("#btnEditCancel").click(function() {
			$("#lightBox").css("display","none");
		})
	</script>	
</body>
</html>