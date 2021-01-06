<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.roomorder.model.*" %>
<% 
  java.sql.Date check_in_date = null;
  try {
	    check_in_date = java.sql.Date.valueOf(request.getParameter("check_in_date").trim());
   } catch (Exception e) {
	    check_in_date = new java.sql.Date(System.currentTimeMillis());
   }
  
  java.sql.Date check_out_date = null;
  try {
	    check_out_date = java.sql.Date.valueOf(request.getParameter("check_out_date").trim());
   } catch (Exception e) {
	    check_out_date = new java.sql.Date(System.currentTimeMillis());
   }
%>
<%
	RoomOrderService roomOrderSvc = new RoomOrderService();
	List<RoomOrderVO> list = roomOrderSvc.getAllRO();
	pageContext.setAttribute("list", list);
%>
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
	background-color:#c15c61 !important;
}
#myTable th {
	background-color:#000;
	color:#fff;
	position:sticky;
	top:0;
}
</style>
</head>
<body>
	<header>
		<h2 style="text-align:center; margin-bottom:20px;">訂房訂單資訊</h2>
	</header>
	<div style="height:43em; overflow:scroll;">
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
			<tr class="hover">	
				<td><a class="linkit" href="<%=request.getContextPath()%>/back-end/room-order/RoomOrderDetail.jsp?room_order_id=${roomOrderVO.room_order_id}&mem_id=${roomOrderVO.mem_id}">${roomOrderVO.room_order_id}</a></td>
				<td>${roomOrderVO.mem_id}</td>
				<td>${roomOrderVO.check_in_date}</td>
				<td>${roomOrderVO.check_out_date}</td>
				<td><c:choose>
					<c:when test="${roomOrderVO.status == '0'}">
						已付款
					</c:when>
					<c:otherwise>
						入住中
					</c:otherwise>
					</c:choose></td>
				<td colspan="2"><i class="edit fas fa-edit"></i><i style="margin-left:20px;" class="delete far fa-trash-alt"></i></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div id="showBox" style="display:none;">
		<i class="cancel fas fa-window-close fa-2x"></i>
		<iframe id="show" src="" width="30%" height="570"></iframe>
	</div>
	<div id="lightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/RoomOrder.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>訂單修改</td></tr>
					<tr><td>訂單編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="room_order_id" class="input-noEdit" type="text" name="room_order_id" readonly></td></tr>			
					<tr><td>會員編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="mem_id" class="input-noEdit" type="text" name="mem_id" readonly></td></tr>
					<tr><td>入住日期：</td><td><input id="checkin" class="input-beautify" type="text" name="check_in_date" required></td></tr>
					<tr><td>退房日期：</td><td><input id="checkout" class="input-beautify" type="text" name="check_out_date" required></td></tr>
					<tr><td>訂單狀態：</td>
						<td>
						<select id="status" class="input-beautify" type="text" name="status" required>
							<option>請選擇狀態</option>
							<option value="0">未完單</option>
							<option value="1">已完單</option>
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
			
		<script>
		$.datetimepicker.setLocale('zh');
        $('#checkin').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=check_in_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#checkout').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=check_out_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '<%=check_in_date%>' // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
		$(".edit").click(function() {
			$("#lightBox").css("display","");
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#room_order_id").val(children.eq(0).text());
			$("#mem_id").val(children.eq(1).text());
			$("#checkin").val(children.eq(2).text());
			$("#checkout").val(children.eq(3).text());
			$("#status").val("請選擇狀態");
		})
		
		$("#btnEditCancel").click(function() {
			$("#lightBox").css("display","none");
		})
		
		//連結訂單明細
		$(".linkit").click(function(e){
			e.preventDefault();
			$("#showBox").css("display","");
			let url = $(this).attr('href');
			$("#show").attr('src',url);
		})
		
		$(".cancel").click(function(){
			$("#showBox").css("display","none");
		})
	</script>	
<%@include file="/back-end/back-template/backIndex2.file"%>
</body>
</html>