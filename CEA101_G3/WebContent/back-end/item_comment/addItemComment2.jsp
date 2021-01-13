<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_comment.model.*"%>


<% 
  java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String formatDate = df.format(new java.util.Date());
  Long time = new java.util.Date().getTime();
%>
<%
  ItemCommentVO itemCommentVO = (ItemCommentVO) request.getAttribute("itemCommentVO");
%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
	<c:if test="${not empty errorMsgs}">
	<script>
		$("#tab-3").prop("checked",true);
		swal("新增失敗","請修正錯誤！","error");	
	</script>		
	</c:if>
	<c:if test="${repeat == 'repeat'}">
	<script>
		$("#tab-3").prop("checked",true);
		swal("編號重複","請修正錯誤！","error");
	</script>
	</c:if>
	<c:if test="${insertSuccess == 'yes' }">
		<script>
			$("#tab-3").prop("checked",true);
			swal("新增成功", "請查看商品評論", "success");
		</script>
	</c:if>
</head>
<body>
	
	<jsp:useBean id="now" scope="page" class="java.util.Date" /> 
	<form method="post" action="<%=request.getContextPath()%>/item_comment/itemComment.do">
		<div id="content-2">
		
			評論編號：<input class="input-beautify" type="text" name="itemCommentId" value="">
			<c:if test="${not empty errorMsgs}">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.itemCommentId}</p>
			</c:if>
			<p></p>
			商品名稱：<input class="input-beautify" type="text" name="itemId" value="">
			<c:if test="${not empty errorMsgs}">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.itemId}</p>
			</c:if>
			<p></p>
			評論內容：<input class="input-beautify" type="text" name="shopComment" value="">
			<c:if test="${not empty errorMsgs}">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.shopComment}</p>
			</c:if>
			<p></p>
			評論時間：<input class="input-beautify" type="TEXT" name="timimg" disabled="disabled" value="<%=formatDate%>" />
			 		  <input type="hidden" name="time" value="<%=time%>" />
	
			<input type="hidden" name="action" value="insert"><br>
			<button id="add" type="submit" class="btn btn-primary">新增商品評論</button>
		</div>
	</form>
</body>
</html>