<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room_comment.model.*"%>
<!DOCTYPE html>
<%@ include file="/back-end/back-template/backIndex.file"%>
<html>
<head>
<meta charset="UTF-8">
<title>房間評論管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/room_comment.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backend/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<a href="<%=request.getContextPath()%>/back-end/room_comment/select_page.jsp"><h2 style="text-decoration: underline; text-underline-position: under; color:#675157; padding:5px 20px 20px 20px;">房間評論管理</h2></a>
	<div id="container">
		<input class="title" id="tab-1" type="radio" name="tab-group" checked="checked"/>
		<label for="tab-1">所有評論</label> 
		
		<input class="title" id="tab-5" type="radio" name="tab-group" />
		<label for="tab-5">已回覆評論</label>
		
		<input class="title" id="tab-6" type="radio" name="tab-group" />
		<label for="tab-6">未回覆評論</label>
		
		<div id="content">
			<jsp:include page="listAllRoomComment.jsp" />
			<jsp:include page="listAllReplyRoomComment.jsp" />
			<jsp:include page="listAllWaitReplyRoomComment.jsp" />
		</div>
	</div>
<%@ include file="/back-end/back-template/backIndex2.file"%>
</body>
</html>