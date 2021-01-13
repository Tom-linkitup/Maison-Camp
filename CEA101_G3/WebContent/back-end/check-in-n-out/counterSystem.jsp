<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	List<RoomOrderVO> checkOuts = roSvc.getAllCheckOutOrder(); //取得當天尚未CheckOut的訂單
	pageContext.setAttribute("checkIns", checkIns);
	pageContext.setAttribute("checkOuts", checkOuts);
	
	RoomService rmSvc = new RoomService();
	List<RoomVO> rmlist = rmSvc.getAllRM();
	Integer roomCount = rmlist.size();
	Integer occupyCount = 0;	
	for(RoomVO rm : rmlist){
		if(rm.getOccupy() == 1){
			occupyCount++;
		}
	}
	Integer roomRate = Math.round((occupyCount*100) / roomCount);
	pageContext.setAttribute("roomRate", roomRate);
	pageContext.setAttribute("occupyCount", occupyCount);
%>


<!DOCTYPE html>
<%@ include file="/back-end/back-template/backIndex.file"%>
<html lang="en">
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/back-end/counterSystem.css">
<title>房務系統</title>
</head>
<body>
	<div class="status">
		<h4>客房預訂管理</h4>
	</div>
	<div class="header">
			<div class="current">
				<div>
					<p>${checkIns.size()}</p>
					<i class="fal fa-bell fa-3x"></i>
				</div>
				<h4>今日待入住訂單</h4>
			</div>
			<div class="current">
				<div>
					<p style="color:red;">${checkOuts.size()}</p>
					<i class="fal fa-sign-out-alt fa-3x"></i>
				</div>
				<h4>今日待退房訂單</h4>
			</div>
			<div class="current">
				<div>
					<p style="color:#1776b9;">${occupyCount}</p>
					<div class="percentage">${roomRate}%</div>
				</div>
				<h4>今日住房數</h4>
			</div>
	</div>
	
	<div id="container">
		<input class="title" id="tab-1" type="radio" name="tab-group" checked="checked"/>
		<label for="tab-1">入住訂單</label> 
		
		<input class="title" id="tab-2" type="radio" name="tab-group" />
		<label for="tab-2">退房訂單</label> 
		
		<input class="title" id="tab-3" type="radio" name="tab-group"  /> 
		<label for="tab-3">入住中訂單</label>
		
		<div id="content">
			<jsp:include page="CheckIn.jsp" />
			<jsp:include page="CheckOut.jsp" />
			<jsp:include page="InRoom.jsp" />
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
<%@ include file="/back-end/back-template/backIndex2.file"%>
</body>
</html>
