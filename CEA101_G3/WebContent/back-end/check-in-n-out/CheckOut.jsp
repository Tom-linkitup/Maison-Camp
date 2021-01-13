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
					<td>
						<c:choose>
							<c:when test="${checkOut.status != 2}">
								<a class="linkit" href="<%=request.getContextPath()%>/back-end/check-in-n-out/Receipt.jsp?room_order_id=${checkOut.room_order_id}&mem_id=${checkOut.mem_id}">查看消費明細</a>
							</c:when>
							<c:otherwise>
								待退房
							</c:otherwise>
						</c:choose>
                    </td>
					<td><button class="checkout-confirm btn btn-secondary" <c:if test="${checkOut.status != 2}">disabled</c:if>>CHECK OUT</button></td>
					
				</tr>
			</c:forEach>	
		</table>
		<div id="showBox" style="display:none;">
			<i class="cancel fas fa-window-close fa-2x"></i>
			<iframe id="show" src="" width="45%" height="550"></iframe>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//連結收據明細
			$(".linkit").click(function(e){
				e.preventDefault();
				$("#showBox").css("display","");
				let url = $(this).attr('href');
				$("#show").attr('src',url);
			})
			
			$(".cancel").click(function(){
				$("#showBox").css("display","none");
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
		})
	</script>
</body>
</html>