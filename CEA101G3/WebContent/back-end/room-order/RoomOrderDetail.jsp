<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.roomorder.model.*" %>
<%@ page import="com.roomorderdetail.model.*" %>
<%@ page import="com.roomtype.model.*" %>
<%@ page import="com.member.model.*" %>
<!DOCTYPE html>

<%
	String room_order_id = request.getParameter("room_order_id");
	String mem_id = request.getParameter("mem_id");

	RoomOrderDetailService rodSvc = new RoomOrderDetailService();
	RoomOrderDetailVO roomOrderDetailVO = rodSvc.getOneROD(room_order_id);
	pageContext.setAttribute("roomOrderDetailVO", roomOrderDetailVO);
	
	RoomOrderService roSvc = new RoomOrderService();
	RoomOrderVO roomOrderVO = roSvc.getOneRO(room_order_id);
	pageContext.setAttribute("roomOrderVO", roomOrderVO);
	
	MemberService memSvc = new MemberService();
	MemberVO memVO = memSvc.getOneMEM(mem_id);
	pageContext.setAttribute("memVO", memVO);
	
	RoomTypeService rtSvc = new RoomTypeService();
	RoomTypeVO roomTypeVO = rtSvc.getOneRT(roomOrderDetailVO.getRoom_category_id());
	pageContext.setAttribute("roomTypeVO", roomTypeVO);
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/room_order_detail.css"/>
<title>Insert title here</title>
</head>
<body>
	<div id="invoiceholder">
	  	<div id="invoice" class="effect2"> 
		    <div id="invoice-top">
		    <div class="logo"></div> 
		      <div class="title">
		        <h1>訂單編號: #${roomOrderDetailVO.room_order_id}</h1>
		        <p>房型編號: ${roomOrderDetailVO.room_category_id}</p>
		        <p>房型名稱: ${roomTypeVO.room_name}</p>
		        <p>入住日期: ${roomOrderVO.check_in_date}</p>
		        <p>退房日期: ${roomOrderVO.check_out_date}</p>
		        <p>訂房數量: ${roomOrderDetailVO.quantity} 間</p>
		        <p>房間價格: ${roomOrderDetailVO.room_order_price} 元</p>
		        <p>訂單日期: ${roomOrderDetailVO.order_time}</p>
		       
		      </div>
		    </div>
		    <div id="invoice-mid">
		    <div class="clientlogo"></div>
		      <div class="title">
		        <h1>會員編號: #${memVO.mem_id}</h1>
		        <p>會員名稱: ${memVO.name}</p>
		        <p>聯絡方式: ${memVO.email}</p>
		        <p>付款卡號: ${memVO.payment}</p>
		      </div> 
		    </div>
	  	</div>
	</div>	
</body>
</html>