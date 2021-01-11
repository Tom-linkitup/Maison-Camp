<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.roomorder.model.*"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDate"%>

<%
	RoomOrderService roSvc = new RoomOrderService();
	List<RoomOrderVO> checkIns = roSvc.getAllCheckInOrder(); //取得當天要checkIn的訂單
	pageContext.setAttribute("checkIns", checkIns);
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
	<div id="content-1" style="width:100%;">
		<table class="table table-striped" style="text-align:center;">
			<tr class="table-head">
				<th>訂單編號</th>
				<th>入住會員</th>
				<th>入住日期</th>
				<th>退房日期</th>
				<th>辦理入住</th>
			</tr>
			<c:if test="${checkIns.size()==0}">
				<tr>
					<td colspan="7" class="td-msg">今日無待入住客戶</td>
				</tr>
			</c:if>
			
			<c:forEach var="checkIn" items="${checkIns}">
				<tr class="list-data">
					<td>${checkIn.room_order_id}</td>
					<td>${memSvc.getOneMEM(checkIn.mem_id).name}</td>
					<td>${checkIn.check_in_date}</td>
					<td>${checkIn.check_out_date}</td>
					<td><button class="check-in btn btn-secondary" <c:if test="${checkIn.status != 0}">disabled</c:if> >CHECK IN</button></td>
				</tr>
			</c:forEach>
		</table>
		<div id="lightBox" style="display:none;">
			<div id="roomShow">
				<h3 id="roomName" style="color:#6b8c57; text-align:center; margin-top:20px;"></h3>
				<h4>選擇房號:</h4>
				<div class="col-sm-12 roomList">		
				</div>
				<div class="roomBtn">
				<button class="checkin-confirm btn btn-info">確認</button>
				<button class="cancel btn btn-danger">取消</button>	
				</div>
			</div>				
		</div>
	</div>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".check-in").click(function(){
			$(".roomList").html("");
			$("#lightBox").css("display","");
			let room_order_id = $(this).parent().parent().find("td").eq(0).html();
			$.ajax({
				url: "<%=request.getContextPath()%>/RoomOrder.do?action=getAvailableRoom",
				data: {room_order_id: room_order_id,},
				type: "POST",
				success: function(str){
					let data = JSON.parse(str);
					$("#roomName").text(data.room_name);
					for(i in data.rmlist){
						if(data.rmlist[i].status === 0){ //房間為可check in狀態
							let roomBox = document.createElement("div");
							roomBox.classList.add("roomBox");
							roomBox.innerText = data.rmlist[i].room_id;
							$(".roomList").append(roomBox);								
						}
					}
					//預設選擇第一間
					let roomBoxes = $(".roomBox");
					roomBoxes[0].classList.add("selected");
					
					$(".roomBox").click(function(){		
				        $(this).siblings(".roomBox").removeClass("selected");
				        $(this).addClass("selected");
					})
					
					//將選好的房號放入房間，並更改房間狀態
					$(".checkin-confirm").click(function(){
						let selected_room_id = $(".selected").html();
						let room_order_id_again = data.room_order_id;
						$.ajax({
							url: "<%=request.getContextPath()%>/RoomOrder.do?action=updateRoom",
							data: {
								selected_room_id: selected_room_id,
								room_order_id: room_order_id_again,
							},
							type: "POST",
							success: function(str){
								if(str === "success"){
									swal("入住成功","please confirm","success");
								 	setTimeout(function () {
				                        window.location.reload();
				                    }, 1000);
								}
							}
						})
					})				
				}
			})
		});
			
		$(".cancel").click(function(){
			$("#lightBox").css("display","none");
		})	
	})


</script>
</body>
</html>