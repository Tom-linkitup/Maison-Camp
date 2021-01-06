<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ page import="com.item_comment.model.*" %>
<%@ include file="/back-end/back-template/backIndex.file" %> 
<!DOCTYPE html>
<%
  ItemCommentVO itemCommentVO = (ItemCommentVO) request.getAttribute("itemCommentVO"); //ItemServlet.java(Concroller), 存入req的empVO物件
%>
<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />

<html>
<head>
<title>商品資料 - listOneItemComment.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor="white">

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneItemComment.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/item_comment/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品評論編號</th>
		<th>商品編號</th>
		<th>商品評論</th>
		<th>評論時間</th>
	</tr>
	<tr>
		<td>${itemCommentVO.itemCommentId}</td>
		<td>${itemCommentVO.itemId}</td>
		<td>${itemCommentVO.shopComment}</td>
		<td>${itemCommentVO.time}</td>
	</tr>
</table>

</body>
</html>
<%@ include file="/back-end/back-template/backIndex2.file" %> 