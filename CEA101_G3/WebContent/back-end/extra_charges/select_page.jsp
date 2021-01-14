<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.extra_charges.model.*"%>
<!DOCTYPE html>
<%@ include file="/back-end/back-template/backIndex.file"%>
<html>
<head>
<meta charset="UTF-8">
<title>額外消費管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/extra_charges.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backend/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<a href="<%=request.getContextPath()%>/back-end/extra_charges/select_page.jsp"><h2 style="text-decoration: underline; text-underline-position: under; color:#675157; padding:5px 20px 20px 20px;">額外消費管理</h2></a>
	<div id="container">
		<input class="title" id="tab-1" type="radio" name="tab-group" checked="checked"/>
		<label for="tab-1">查看所有額外消費明細</label> 
		
	  	<input class="title" id="tab-3" type="radio" name="tab-group"  /> 
		<label for="tab-3">未退房</label>
		
		<input class="title" id="tab-4" type="radio" name="tab-group"  /> 
		<label for="tab-4">已退房</label>
		
		
		<input class="title" id="tab-2" type="radio" name="tab-group"  /> 
		<label for="tab-2">新增額外消費</label>
		
		
		<div id="content">
			<jsp:include page="listAllExtraCharges.jsp" />
			
			
			<jsp:include page="addExtraCharges.jsp" />
			
		</div>
	</div>
	<%@ include file="/back-end/back-template/backIndex2.file"%>
</body>
</html>