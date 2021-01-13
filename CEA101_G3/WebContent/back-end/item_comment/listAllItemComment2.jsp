<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%@ page import="com.item_comment.model.*" %>
<%@ page import="com.item_category.model.*"%>
<%
ItemCommentService itemCommentSvc = new ItemCommentService();
	List<ItemCommentVO> list = itemCommentSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<% 
  java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String formatDate = df.format(new java.util.Date());
  Long time = new java.util.Date().getTime();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="content-1">
		<h2 style="text-align:center; margin-bottom:20px;">商品資訊</h2>
		<table id="myTable" border="1px solid #000">
			<tr class="header">
				
				<th>商品類別編號</th>
				<th>商品類別名稱</th>
				<th>商品修改</th>
				<th>商品刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="itemCommentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">			
			<tr>
				<td>${itemCommentVO.itemCommentId}</td>
				<td>${itemCommentVO.itemId}</td>
				<td>${itemCommentVO.shopComment}</td>
				<td>${itemCommentVO.time}</td>
				<td>
				<input type="hidden" name="itemCommentId" value="${itemCommentVO.itemCommentId}">
				<input type="hidden" name="action" value="getOne_For_Update">	
			    <button class="edit btn btn-info" type="submit">修改</button>
				</td>
				<td>
				<form method="post" action="<%=request.getContextPath()%>/item_comment/itemComment.do">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="itemCommentId" value="${itemCommentVO.itemCommentId}">
				    <button class="delete btn btn-danger" type="submit">刪除</button>
				</form>
				</td>
			</tr>
			</c:forEach>	
		</table>
		<%@ include file="page2.file"%>
		<div id="lightBox" style="display:none;">
			<form method="post" action="${pageContext.request.contextPath}/item_comment/itemComment.do">
				<table align="center" id="tableLogin">
					<tr style="font-size:20px; color:#c15c61;"><td>商品修改</td></tr>
					<tr><td>評論編號：</td><td><input style="background-color:#f9f9f9; border:none;" id="itemCommentId" class="input-noEdit" type="text" name="itemCommentId" readonly></td></tr>			
					<tr><td>商品名稱：</td><td><input id="itemId" class="input-beautify" type="text" name="itemId" readonly></td></tr>
					<tr><td>評論內容：</td><td><input id="shopComment" class="input-beautify" type="text" name="shopComment" required></td></tr>
					<tr><td>評論時間：</td><td><input class="input-beautify" type="TEXT" name="timimg" disabled="disabled" value="<%=formatDate%>" />
			 		  						<input type="hidden" name="time" value="<%=time%>" /></td></tr>
					<tr><td colspan="2" align="center">
					<input type="hidden" name="action" value="update">
					<input class="btn btn-info" type="submit" id="btnEdit" value="送出修改">
					<input class="btn btn-warning" type="button" id="btnEditCancel" value="取消">
				</table>
			</form>
		</div>
	</div>
	<script>		
		$(".edit").click(function() {
			$("#lightBox").css("display","");
			let tr = $(this).parents("tr");
			let children = tr.children();
			$("#itemCommentId").val(children.eq(0).text());
			$("#itemId").val(children.eq(1).text());
			$("#shopComment").val(children.eq(2).text());
			$("#time").val(children.eq(3).text());
		})
		
		$("#btnEditCancel").click(function() {
			$("#lightBox").css("display","none");
		})
	</script>	
</body>
</html>