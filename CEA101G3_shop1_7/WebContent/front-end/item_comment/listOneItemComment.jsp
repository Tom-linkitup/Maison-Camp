<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.item_comment.model.*" %>
<!DOCTYPE html>
<%
  ItemCommentVO itemCommentVO = (ItemCommentVO) request.getAttribute("itemCommentVO"); //ItemServlet.java(Concroller), �s�Jreq��empVO����
%>
<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />

<html>
<head>
<title>�ӫ~��� - listOneItemComment.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneItemComment.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�ӫ~���׽s��</th>
		<th>�ӫ~�s��</th>
		<th>�ӫ~����</th>
		<th>���׮ɶ�</th>
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