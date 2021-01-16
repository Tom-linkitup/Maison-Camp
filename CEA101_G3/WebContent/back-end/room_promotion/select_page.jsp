<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room_promotion.model.*"%> 
<!DOCTYPE html>
<%@ include file="/back-end/back-template/backIndex.file"%>
<html>
<head>
<meta charset="UTF-8">
<title>訂房優惠管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/room_promotion.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backend/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
</head>
<body>
<%
    Room_promotionVO room_promotionVO = (Room_promotionVO) request.getAttribute("room_promotionVO");
%>
<%-- <!--  ====<%=(room_promotionVO != null)?room_promotionVO.getRoom_promotion_info():"" %>==== 測試用--> --%>

	<a href="<%=request.getContextPath()%>/back-end/room_promotion/select_page.jsp"><h2 style="text-decoration: underline; text-underline-position: under; color:#675157; padding:5px 20px 20px 20px;">訂房優惠管理</h2></a>
	<div id="container">
	
		<input class="title" id="tab-1" type="radio" name="tab-group" checked="checked"/>
		<label for="tab-1">查看所有訂房優惠</label> 
		
		<input class="title" id="tab-3" type="radio" name="tab-group" /> 
		<label for="tab-3">進行中優惠</label> 
		
		<input class="title" id="tab-5" type="radio" name="tab-group" /> 
		<label for="tab-5">未開始優惠</label> 
		
		<input class="title" id="tab-4" type="radio" name="tab-group" /> 
		<label for="tab-4">已結束優惠</label>
		
		<input class="title" id="tab-2" type="radio" name="tab-group" /> 
		<label for="tab-2">新增訂房優惠</label> 
		
		<div id="content">
			<jsp:include page="listAllRoomPromotion.jsp" />
			<jsp:include page="listNowRoomPromotion.jsp" />
			<jsp:include page="listPastRoomPromotion.jsp" />
			<jsp:include page="listFutureRoomPromotion.jsp" />
			<jsp:include page="addRoomPromotion.jsp" />
		</div>
	</div>
  <%@ include file="/back-end/room_promotion/backIndex2.file"%> 
</body>
</html>