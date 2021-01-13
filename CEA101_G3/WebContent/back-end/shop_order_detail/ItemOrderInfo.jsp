<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_photo.model.*" %>
<%@ page import="com.item_category.model.*" %>
<!DOCTYPE html>
<%@ include file="/back-end/back-template/backIndex.file"%>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/item.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<a href="${pageContext.request.contextPath}/back-end/item/ItemInfo.jsp"><h2 style="text-decoration: underline; text-underline-position: under; color:#675157; padding:5px 20px 20px 20px;">商品訂單管理</h2></a>
	<div id="container">
		<input class="title" id="tab-1" type="radio" name="tab-group" checked="checked"/>
		<label for="tab-1">查看所有訂單</label> 
		
		<div id="content">
			<jsp:include page="/back-end/shop_order_detail/listAllItemOrder.jsp" />
		</div>
	</div>
<%-- 	<%@ include file="/back-end/item/backIndex2.file"%> --%>
</body>
</html>
<%@ include file="/back-end/back-template/backIndex2.file"%>