<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_photo.model.*"%>
<%
  ItemPhotoVO itemPhotoVO = (ItemPhotoVO) request.getAttribute("itemPhotoVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品照片資料新增 - addItem.jsp</title>
</head>
<body bgcolor="white">


<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM method="post" action="<%=request.getContextPath() %>/item_photo/itemPhoto.do" enctype="multipart/form-data" >
<div id="content-2">
<table>

	<jsp:useBean id="ItemSvc" scope="page" class="com.item.model.ItemService" />
	<tr>
		<td>商品:<font color=red><b>*</b></font></td>
		<td><select size="1" name="itemId">
			<c:forEach var="itemVO" items="${ItemSvc.all}">
				<option value="${itemVO.itemId}" ${(itemPhotoVO.itemId==itemVO.itemId)? 'selected':'' } >${itemVO.itemName}
			</c:forEach>
		</select></td>
		<td><img ></td>
	</tr>
	
	<tr>
		<td>商品照片:<font color=red><b>*</b></font></td>
		<td>
			<input type="file" name="content" multiple required>
		</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</div>
</FORM>
</body>
</html>