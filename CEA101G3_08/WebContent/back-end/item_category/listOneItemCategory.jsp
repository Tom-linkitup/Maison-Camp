<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.item_category.model.*" %>
<!DOCTYPE html>
<%
ItemCategoryVO itemCategoryVO = (ItemCategoryVO) request.getAttribute("itemCategoryVO"); //ItemServlet.java(Concroller), �s�Jreq��empVO����
%>
<%@ include file="backIndex.file" %> 
<jsp:useBean id="itemCategorySvc" scope="page" class="com.item_category.model.ItemCategoryService" />

<html>
<head>
<title>�ӫ~��� - listOneItemCategory.jsp</title>

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
		 <h3>���u��� - ListOneItemCategory.jsp</h3>
		 <h4><a href="<%=request.getContextPath() %>/back-end/item_category/select_page.jsp"><img src="<%=request.getContextPath() %>images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�ӫ~���O�s��</th>
		<th>�ӫ~���O�W��</th>
	</tr>
	<tr>
		<td>${itemCategoryVO.itemCategoryId}</td>
		<td>${itemCategoryVO.itemCategoryName}</td>
	</tr>
</table>

</body>
</html>
<%@ include file="backIndex2.file" %> 