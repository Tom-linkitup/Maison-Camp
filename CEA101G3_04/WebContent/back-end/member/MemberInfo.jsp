<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<!DOCTYPE html>
<%@ include file="backIndex.file"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/member.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<a href="${pageContext.request.contextPath}/back-end/member/MemberInfo.jsp"><h2 style="text-decoration: underline; text-underline-position: under; color:#675157; padding:5px 20px 20px 20px;">會員管理</h2></a>
	<div id="container">
		<input class="title" id="tab-1" type="radio" name="tab-group" checked="checked"/>
		<label for="tab-1">查看所有會員</label> 
		
		<input class="title" id="tab-2" type="radio" name="tab-group" /> 
		<label for="tab-2">預設空白</label> 
		
		<input class="title" id="tab-3" type="radio" name="tab-group"  /> 
		<label for="tab-3">預設空白</label>
		
		<div id="content">
			<jsp:include page="CheckMember.jsp" />
			<jsp:include page="Default.jsp" />
			<jsp:include page="Default2.jsp" />
		</div>
	</div>
	<%@ include file="backIndex2.file"%>
</body>
</html>