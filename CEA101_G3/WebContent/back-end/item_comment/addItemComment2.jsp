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
		swal("�s�W����","�Эץ����~�I","error");	
	</script>		
	</c:if>
	<c:if test="${repeat == 'repeat'}">
	<script>
		$("#tab-3").prop("checked",true);
		swal("�s������","�Эץ����~�I","error");
	</script>
	</c:if>
	<c:if test="${insertSuccess == 'yes' }">
		<script>
			$("#tab-3").prop("checked",true);
			swal("�s�W���\", "�Ьd�ݰӫ~����", "success");
		</script>
	</c:if>
</head>
<body>
	
	<jsp:useBean id="now" scope="page" class="java.util.Date" /> 
	<form method="post" action="<%=request.getContextPath()%>/item_comment/itemComment.do">
		<div id="content-2">
		
			�ӫ~�s���G<input class="input-beautify" type="text" name="itemId" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.itemId}</p>
			</c:if>
			���פ��e�G<input class="input-beautify" type="text" name="shopComment" value="">
			<p></p>
			<c:if test="${not empty errorMsgs}">
			<p class="error" style="color:red; font-size:8px;">${errorMsgs.shopComment}</p>
			</c:if>
			���׮ɶ��G<input class="input-beautify" type="TEXT" name="timimg" disabled="disabled" value="<%=formatDate%>" />
			 		  <input type="hidden" name="time" value="<%=time%>" />
	
			<input type="hidden" name="action" value="insert"><br>
			<button id="add" type="submit" class="btn btn-primary">�s�W�ӫ~����</button>
		</div>
	</form>
</body>
</html>