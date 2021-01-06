<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_photo.model.*" %>
<%
ItemService itemSvc = new ItemService();
	List<ItemVO> list = itemSvc.getAll();
	pageContext.setAttribute("list", list);
	
	List<ItemPhotoVO> iphList = (List<ItemPhotoVO>) request.getAttribute("iphList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

.itemPic {
	width:230px; 
	height:150px; 
	display:inline-block;
}

.imageWrapper {
	width:100%;
	height:36em;
	overflow:scroll;
	margin-left:50px;
}

.add-border {
	border: 4px solid #c15c61;
}
</style>
<c:if test="${query == 'query' }">
		<script>
			$("#tab-4").prop("checked",true);
		</script>
	</c:if>
	<c:if test="${deletePicSuccess == 'deletePicSuccess'}">
		<script>
			$("#tab-4").prop("checked",true);
		</script>
	</c:if>
</head>
<body>
	<div id="content-4">
		<form method="get" action="${pageContext.request.contextPath}/getItemPhoto.do">
			<div class="">
				<span>請選擇商品：</span>
				<select name="itemId">
						<option id="optionPhoto" value="noSelectInPhoto">請選擇</option>
					<c:forEach var="item" items="${list}">
						<option value="${item.itemId}">${item.itemName}</option>
					</c:forEach>
				</select>
				<button id="queryPhoto" class="btn btn-primary" type="submit">查詢</button>	
				<button id="deletePhoto" class="remove btn btn-danger">刪除</button>		
			</div>
		</form>
		<div class="imageWrapper">
		<c:choose>
			<c:when test="${iphList.size() != 0 }">		
				<c:forEach var="iph" items="${iphList}">
					<div style="margin-bottom:4px; margin-top:10px; display:inline-block">
						<img class="itemPic" src="${pageContext.request.contextPath}/item_photo/photoReader.do?itemPhotoId=${iph.itemPhotoId}">
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<h5 style="color:#c15c61;">＊此商品尚未有圖片</h5>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
<script>
	$(".itemPic").click(function(){
		if ($(this).hasClass("add-border")) {
			$(this).removeClass("add-border");
		} else {
			$(this).addClass("add-border");
		}
	})
	
	$(".remove").click(function(){
		let chooseDel = document.getElementsByClassName("add-border");
		for (let i = 0; i < chooseDel.length; i++) {
			let srcStr = chooseDel[i].getAttribute("src").toString();
			let index = srcStr.indexOf("=") + 1;
			let itemPhotoId = srcStr.substring(index, index+9);
			let formData = new FormData();
			formData.append("itemPhotoId", itemPhotoId);
			formData.append("action", "delete");
			let xhr = new XMLHttpRequest();
			xhr.addEventListener("load", uploadComplete, false);
			xhr.open("POST", "${pageContext.request.contextPath}/item_photo/itemPhoto.do");
			xhr.send(formData);
		}
	})
	
	function uploadComplete(event) {
			location.reload();
	}
	
</script>
</body>
</html>