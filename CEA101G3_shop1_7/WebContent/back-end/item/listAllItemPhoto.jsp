<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item_photo.model.*"%>
<%
	ItemPhotoService itemPhotoSvc = new ItemPhotoService();
    List<ItemPhotoVO> list = itemPhotoSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />

<html>
<head>
<title>所有商品資料 - listAllItemPhoto.jsp</title>

<style>
  img {
  	width: 150px;
  	height: 100px;
  	margin: 1px;
  }
</style>
</head>
<body >

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div id="content-4">
<table>
	<tr>
		<th>商品照片編號</th>
		<th>商品編號</th>
		<th>商品照片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="itemPhotoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${itemPhotoVO.itemPhotoId}</td>
			<td>${itemPhotoVO.itemId}</td>
			<td><img src="<%=request.getContextPath() %>/item_photo/photoReader.do?id=${itemPhotoVO.itemPhotoId}"></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item_photo/itemPhoto.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="itemPhotoId"  value="${itemPhotoVO.itemPhotoId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item_photo/itemPhoto.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="itemPhotoId"  value="${itemPhotoVO.itemPhotoId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<%@ include file="page2.file" %>

</body>
</html>