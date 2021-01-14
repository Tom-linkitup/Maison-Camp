<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room_promotion.model.*"%>
<%@ page import="com.roomtype.model.*"%>

<%
	Room_promotionService room_promotionSvc = new Room_promotionService();
	List<Room_promotionVO> list = room_promotionSvc.getAllFuture();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content-5">
		<h2 style="text-align:center; margin-bottom:20px;">訂房優惠資訊</h2>
		<table id="myTable" border="1px solid #000"  >
			<tr class="header" >
				<th>訂房優惠編號</th>
				<th>優惠房型名稱</th>
				<th>優惠資訊</th>
				<th>優惠折扣</th>
				<th>優惠開始日期</th>
				<th>優惠結束日期</th>
				<th>訂房優惠修改</th>
			</tr>
			<%@ include file="page1.file" %> 
			<jsp:useBean id="roomTypeSvc" scope="page" class="com.roomtype.model.RoomTypeService" />
			
			<c:forEach var="room_promotionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">			
			
			
			<tr>
				<td>${room_promotionVO.room_promotion_id}</td>
				<td><c:forEach var="roomTypeVO" items="${roomTypeSvc.allRT}"><c:if test="${room_promotionVO.room_category_id==roomTypeVO.room_category_id}">${room_promotionVO.room_category_id},【${roomTypeVO.room_name}】	</c:if></c:forEach></td>					
				<td>${room_promotionVO.room_promotion_info}</td>
				<td>${room_promotionVO.room_discount}</td>
				<td>${room_promotionVO.room_prom_start_date}</td>
				<td>${room_promotionVO.room_prom_end_date}</td>	
				<td>
				<input type="hidden" name="room_promotion_id" value="${room_promotionVO.room_promotion_id}">
				<input type="hidden" name="action" value="getOne_For_Update">	
			    <button class="future btn btn-info" type="button">修改</button>
				</td>
			</tr>
			</c:forEach>	
		</table>
		 <%@ include file="page2.file" %> 
		<div id="futureLightBox" style="display:none;">
			<form method="post" action="<%= request.getContextPath() %>/room_promotion/room_promotion.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>訂房優惠修改</td></tr>
					<tr><td>優惠編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_promotion_idf" class="input-beautify" type="text" name="room_promotion_id" readonly></td></tr>			
					<tr><td>房型設定：</td><td><input style="background-color:#f9f9f9; border:none;"id="room_category_idf" class="input-beautify" type="text" name="room_category_id" readonly></td></tr>
					<tr><td>優惠資訊：</td><td><input id="room_promotion_infof" class="input-beautify" type="text" name="room_promotion_info" required></td></tr>
					<tr><td>折扣設定：</td><td><input type="NUMBER" min="0" max="1" step="0.01" id="room_discountf" class="input-beautify"  name="room_discount" required></td></tr>
					<tr><td>優惠開始日期：</td><td><input id="room_prom_start_datef" class="input-beautify" type="date" name="room_prom_start_date" required></td></tr>
					<tr><td>優惠結束日期：</td><td><input id="room_prom_end_datef" class="input-beautify" type="date" name="room_prom_end_date" required></td></tr>
				<tr><td>
					<input type="hidden" name="action" value="update">
					<input class="btn btn-info" type="submit" id="btnEdit" value="送出修改">
					<input class="btn btn-warning" type="button" id="btnEditCancel3" value="取消">
				</table>
			</form>
		</div>
	</div>
	<script>		
		$(".future").click(function() {
			$("#futureLightBox").css("display","");
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#room_promotion_idf").val(children.eq(0).text());
			$("#room_category_idf").val(children.eq(1).text());
			$("#room_promotion_infof").val(children.eq(2).text());
			$("#room_discountf").val(children.eq(3).text());
			$("#room_prom_start_datef").val(children.eq(4).text());
			$("#room_prom_end_datef").val(children.eq(5).text());
		})
		
		$("#btnEditCancel3").click(function() {
			$("#futureLightBox").css("display","none");
		})
		
		
	</script>	
</body>
</html>