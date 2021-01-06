<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomtype.model.*"%>
<%@ page import="com.roomphoto.model.*" %>
<!DOCTYPE html>
<%@ include file="/back-end/back-template/backIndex.file"%>
<html>
<head>
<meta charset="UTF-8">
<title>房型管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/room_type.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<a href="${pageContext.request.contextPath}/back-end/room-type/RoomTypeInfo.jsp"><h2 style="text-decoration: underline; text-underline-position: under; color:#675157; padding:5px 20px 20px 20px;">房型管理</h2></a>
	<div id="container">
		<input class="title" id="tab-1" type="radio" name="tab-group" checked="checked"/>
		<label for="tab-1">查看所有房型</label> 
		
		<input class="title" id="tab-2" type="radio" name="tab-group" />
		<label for="tab-2">新增房型照片</label> 
		
		<input class="title" id="tab-3" type="radio" name="tab-group"  /> 
		<label for="tab-3">新增房型</label>
		
		<input class="title" id="tab-4" type="radio" name="tab-group"  /> 
		<label for="tab-4">查看房型照片</label>
		
		<div id="content" style="height:40em; overflow:scroll;">
			<jsp:include page="CheckRoomType.jsp" />
			<jsp:include page="AddRoomTypePhoto.jsp" />
			<jsp:include page="AddRoomType.jsp" />
			<jsp:include page="showAllRoomPhoto.jsp" />
		</div>
	</div>
	<%@ include file="/back-end/back-template/backIndex2.file"%>
</body>
</html>