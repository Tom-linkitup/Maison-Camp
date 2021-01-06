<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item_comment.model.*"%>
<%@ include file="/back-end/back-template/backIndex.file" %> 
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ItemCommentService itemCommentSvc = new ItemCommentService();
    List<ItemCommentVO> list = itemCommentSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />

<html>
<head>
<title>所有商品資料 - listAllItemComment.jsp</title>

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
	width: 800px;
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
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有商品資料 - listAllItemComment.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/item_comment/select_page.jsp"><img src="images/2.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>商品評論編號</th>
		<th>商品編號</th>
		<th>商品評論</th>
		<th>評論時間</th>

		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="itemCommentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${itemCommentVO.itemCommentId}</td>
			<td>${itemCommentVO.itemId}</td>
			<td>${itemCommentVO.shopComment}</td>
			<td>${itemCommentVO.time}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item_comment/itemComment.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="itemCommentId"  value="${itemCommentVO.itemCommentId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item_comment/itemComment.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="itemCommentId"  value="${itemCommentVO.itemCommentId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
<%@ include file="/back-end/back-template/backIndex2.file" %> 