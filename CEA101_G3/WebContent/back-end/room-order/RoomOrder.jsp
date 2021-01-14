<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.roomorder.model.*" %>
<%
	RoomOrderService roomOrderSvc = new RoomOrderService();
	List<RoomOrderVO> list = roomOrderSvc.getAllRO();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="rodSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />
<jsp:useBean id="rtSvc" scope="page" class="com.roomtype.model.RoomTypeService" />
<!DOCTYPE html>
<%@ include file="/back-end/back-template/backIndex.file"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/room_order.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<title>Insert title here</title>
<style>
.edit:hover {
	color:lightblue;
	cursor:pointer;
}

.delete:hover {
	color:red;
	cursor:pointer;
}
.hover:hover {
	background-color:#ffbc00 !important;
}
#myTable th {
	background-color:#000;
	color:#fff;
	position:sticky;
	top:0;
}

.displayB {
	display: '';
}

.displayN {
	display: none;
}
</style>
</head>
<body>
	<header>
		<h2 style="text-align:center; margin-bottom:20px;">訂房訂單資訊</h2>
	</header>
	<div style="height:43em; overflow:scroll;">
		<div style="display:inline-flex; position:absolute; top:70px; right:15px;">
			<div style="line-height:2; margin-right:4px;">搜尋訂單</div>
			<form class="form-inline my-2 my-lg-0" action="">
				<input class="form-control mr-sm-2 selectEmpBySomeThing" type="text" placeholder="Search" aria-label="Search">
			</form>	
		</div>
		<table id="myTable" class="table table-striped" style="text-align:center;">
			<tr>
				<th>訂單編號</th>
				<th>會員編號</th>
				<th>入住日期</th>
				<th>退房日期</th>
				<th>訂單狀態</th>
				<th colspan="2">訂單修改</th>
			</tr>
			<c:forEach var="roomOrderVO" items="${list}">
			<tr class="hover colorRow">	
				<td class="id_info"><a class="linkit" href="<%=request.getContextPath()%>/back-end/room-order/RoomOrderDetail.jsp?room_order_id=${roomOrderVO.room_order_id}&mem_id=${roomOrderVO.mem_id}">${roomOrderVO.room_order_id}</a></td>
				<td class="name_info">${roomOrderVO.mem_id}</td>
				<td>${roomOrderVO.check_in_date}</td>
				<td>${roomOrderVO.check_out_date}</td>
				<td><c:choose>
						<c:when test="${roomOrderVO.status == '0'}">
							已付款
						</c:when>
						<c:when test="${roomOrderVO.status == '1'}">
							已取消
						</c:when>
						<c:when test="${roomOrderVO.status == '2'}">
							入住中
						</c:when>
						<c:when test="${roomOrderVO.status == '3'}">
							已完單
						</c:when>
					</c:choose>
				</td>
				<td colspan="2">
				<form method="post" action="<%=request.getContextPath()%>/RoomOrder.do">
	      			<input type="hidden" name="room_order_id" value="${roomOrderVO.room_order_id}">
	      			<input type="hidden" name="room_category_id" value="${rodSvc.getOneROD(roomOrderVO.room_order_id).room_category_id}">
	      			<input type="hidden" name="quantity" value="${rodSvc.getOneROD(roomOrderVO.room_order_id).quantity}">
	      			<input type="hidden" name="check_in_date" value="${roomOrderVO.check_in_date}">
	      			<input type="hidden" name="check_out_date" value="${roomOrderVO.check_out_date}">
	      			<input type="hidden" name="action" value="cancelRoomOrderFromBackend">
		      		<button class="btn btn-secondary" type="submit" <c:if test="${roomOrderVO.status == 1}">disabled</c:if>>取消訂單</button>
	      		</form>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div id="showBox" style="display:none;">
		<i class="cancel fas fa-window-close fa-2x"></i>
		<iframe id="show" src="" width="30%" height="570"></iframe>
	</div>	
		<script>
		
		//連結訂單明細
		$(".linkit").click(function(e){
			e.preventDefault();
			$("#showBox").css("display","");
			let url = $(this).attr('href');
			$("#show").attr('src',url);
		})
		
		$(".cancel").click(function(){
			$("#showBox").css("display","none");
		});
		
		$(".selectEmpBySomeThing").keyup(function() {
			let id_info = $('.id_info');
			let name_info = $('.name_info');
			$(".colorRow").addClass('displayN');

			for (let i = 0; i < id_info.length; i++) {
				if (id_info[i].innerText.indexOf($(".selectEmpBySomeThing").val().trim()) != -1 || 
						name_info[i].innerText.indexOf($(".selectEmpBySomeThing").val().trim()) != -1) {
					id_info[i].parentNode.classList.remove('displayN');
					id_info[i].parentNode.classList.add('displayB');
				}
			}
		});
	</script>	
<%@include file="/back-end/back-template/backIndex2.file"%>
</body>
</html>