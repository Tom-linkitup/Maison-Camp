<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_photo.model.*"%>
<%
  ItemPhotoVO itemPhotoVO = (ItemPhotoVO) request.getAttribute("itemPhotoVO");
%>
<%
ItemService itemSvc = new ItemService();
	List<ItemVO> list = itemSvc.getAll();
	pageContext.setAttribute("list", list);
	
	String noSelect = (String)request.getAttribute("noSelect");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:if test="${noSelect == 'noSelect'}">
	<script>
		$("#tab-2").prop("checked",true);
		swal("未選擇商品","請修正錯誤！","error");
	</script>
</c:if>
</head>
<style>
.photo-form {
	position:relative;
	margin-top: 10px;
	margin-left: 20px;
	width: 500px;
	height: 200px;
	border: 4px dashed gray;
}

.photo-form p {
	width: 100%;
	height: 100%;
	text-align: center;
	line-height: 200px;
	color: gray;
	font-family: Arial;
}

.photo-form input {
	position:absolute;
	width: 100%;
	height: 100%;
	outline: none;
	opacity: 0;
	margin-top:20px;
}

.photo-form button {
	margin: 0;
	color: #fff;
	background: #16a085;
	border: none;
	width: 508px;
	height: 35px;
	margin-top: 8px;
	margin-left: -8px;
	border-radius: 4px;
	border-bottom: 4px solid #117A60;
	transition: all .2s ease;
	outline: none;
}

.photo-form button:hover {
	background: #149174;
	color: #0C5645;
}

.photo-form button:active {
	border: 0;
}
</style>
<body>
	<div id="content-2">
		<form class="photo-form" method="post" action="<%=request.getContextPath() %>/item_photo/itemPhoto.do" enctype="multipart/form-data" >
		<jsp:useBean id="ItemSvc" scope="page" class="com.item.model.ItemService" />
		<div class="" style="margin-top:-30px; padding-bottom:7px;">
			<span>請選擇商品：</span>
			<select size="1" name="itemId">
			<c:forEach var="itemVO" items="${ItemSvc.all}">
				<option value="${itemVO.itemId}" } >${itemVO.itemName}
			</c:forEach>
		</select>
		</div>
			<input type="file" name="content" multiple required>
			<p>拖曳照片或直接點擊區塊</p>
			<input type="hidden" name="action" value="insert">
			<button type="submit">上傳</button>
		</form>
	</div>
<script>
$(document).ready(function(){
	  $('.photo-form input').change(function () {
	    $('.photo-form p').text(this.files.length + " file(s) selected");
	  });
	});
</script>
</body>
</html>