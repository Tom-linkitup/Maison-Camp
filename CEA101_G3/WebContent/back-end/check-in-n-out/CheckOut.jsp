<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.roomorderdetail.model.*"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDate"%>

<%
	RoomOrderService roSvc = new RoomOrderService();	
	List<RoomOrderVO> checkOuts = roSvc.getAllCheckOutOrder(); //取得當天尚未CheckOut的訂單
	pageContext.setAttribute("checkOuts", checkOuts);
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="rodSvc" scope="page" class="com.roomorderdetail.model.RoomOrderDetailService" />
<jsp:useBean id="rtSvc" scope="page" class="com.roomtype.model.RoomTypeService" />
<jsp:useBean id="rmSvc" scope="page" class="com.room.model.RoomService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content-2" style="width:100%;">
		<table class="table table-striped" style="text-align:center;">
			<tr class="table-head">
				<th>訂單編號</th>
				<th>會員編號</th>
				<th>入住時間</th>
				<th>退房日期</th>
				<th>入住房號</th>
				<th>訂單金額</th>
				<th>消費明細</th>
				<th>辦理退房</th>
			</tr>
			<c:if test="${checkOuts.size()==0}">
				<tr>
					<td colspan="7" class="td-msg">今日無待退房客戶</td>
				</tr>
			</c:if>
			<c:forEach var="checkOut" items="${checkOuts}">
				<tr class="list-data">
					<td>${checkOut.room_order_id}</td>
					<td>${memSvc.getOneMEM(checkOut.mem_id).name}</td>
					<td>${checkOut.check_in_date}</td>
					<td>${checkOut.check_out_date}</td>
					<td>${checkOut.current_room_id}</td>
					<td>$${rodSvc.getOneROD(checkOut.room_order_id).room_order_price}</td>
					<td><a href="">查看消費明細</a></td>
					<td><button class="checkout-confirm btn btn-secondary" <c:if test="${checkOut.status != 2}">disabled</c:if>>CHECK OUT</button></td>
				</tr>
			</c:forEach>	
		</table>
		<div id="lightBox2" style="display:none;">
			<div id="extraChargeShow">
				<h3 style="color:#6b8c57; text-align:center; margin-top:20px;">新增額外消費明細</h3>
				<div style="margin-left:10%; margin-top:25px;">
					訂單編號:<input class="readonly input-beautify" id="room_order_id_extra" type="text" name="room_order_id" readonly>
					<p></p>
					消費內容:<input class="input-beautify" type="text" name="item" value="" required>
					<p></p>
					消費金額:<input class="input-beautify" type="text" name="price" value="" required>
					<input type="hidden" name="action" value="insertByCheckOut">
					<div class="roomBtn">
					<button type="button" class="extra-confirm btn btn-info">新增</button>
					<button type="button" class="extra-cancel btn btn-danger">取消</button>	
					</div>	
				</div>
			</div>				
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".add-extra").click(function(){
				$("#lightBox2").css("display","");
				let this_room_order_id = $(this).parent().parent().find("td").eq(0).html();
				$("#room_order_id_extra").val(this_room_order_id);
				$("input[name='item']").val("");
				$("input[name='price']").val("");
			});
			
			$(".extra-confirm").click(function(){
				let ajax_room_order_id = $("#room_order_id_extra").val();
				let ajax_item = $("input[name='item']").val();
				let ajax_price = $("input[name='price']").val();
				$.ajax({
					url: "<%=request.getContextPath()%>/extra_charges/extra_charges.do?action=insertByCheckOut",
					data: {
						room_order_id: ajax_room_order_id,
						item: ajax_item,
						price: ajax_price,
					},
					type: "POST",
					success: function(str){
						let data = JSON.parse(str);
						if(data.success === "success"){
							swal("新增成功","請通知付款","success");
							
						}
					}
				})
			});
				
			$(".checkout-confirm").click(function(){
				let room_order_id = $(this).parent().parent().find("td").eq(0).html();
				let room_id = $(this).parent().parent().find("td").eq(4).html();
				$.ajax({
					url: "<%=request.getContextPath()%>/RoomOrder.do?action=checkOut",
					data: {
						room_order_id: room_order_id,
						room_id: room_id,
					},
					type: "POST",
					success: function(msg){
						if(msg === "success"){
							swal("退房成功","請確認","success");
							setTimeout(function () {
		                        window.location.reload();
		                    }, 1000);
						}
					}
				})
			})
			
			$(".extra-cancel").click(function(){
				$("#lightBox2").css("display","none");
			})
		})
		
		
	
	
	
	
	
	</script>
</body>
</html>