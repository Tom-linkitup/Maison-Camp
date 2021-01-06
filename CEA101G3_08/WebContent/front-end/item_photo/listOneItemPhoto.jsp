<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="com.item_photo.model.*" %>
<!DOCTYPE html>
<%
  ItemPhotoVO itemPhotoVO = (ItemPhotoVO) request.getAttribute("itemPhotoVO"); //ItemServlet.java(Concroller), 存入req的empVO物件
%>

<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />

<html>
<head>
<title>商品資料 - listOneItemPhoto.jsp</title>

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
		 <h3>員工資料 - ListOneItemPhoto.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/1.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品照片編號</th>
		<th>商品編號</th>
		<th>商品照片</th>
	</tr>
	<tr>
		<td>${itemPhotoVO.itemPhotoId}</td>
		<td>
			${itemSvc.getOneItem(itemPhotoVO.itemId).itemId} 
		</td>
		<td><img src="photoReader.do?id=${itemPhotoVO.itemPhotoId}"></td>
	</tr>
</table>

</body>
</html>