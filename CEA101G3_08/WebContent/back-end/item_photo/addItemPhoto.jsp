<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_photo.model.*"%>
<%@ include file="backIndex.file" %> 
<%
  ItemPhotoVO itemPhotoVO = (ItemPhotoVO) request.getAttribute("itemPhotoVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品照片資料新增 </title>

</head>
<body bgcolor="white">
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
<table>

	<jsp:useBean id="ItemSvc" scope="page" class="com.item.model.ItemService" />
	<tr>
		<td>商品:<font color=red><b>*</b></font></td>
		<td><select size="1" name="itemId">
			<c:forEach var="itemVO" items="${ItemSvc.all}">
				<option value="${itemVO.itemId}" ${(itemPhotoVO.itemId==itemVO.itemId)? 'selected':'' } >${itemVO.itemName}
			</c:forEach>
		</select></td>
	</tr>
	
	<tr>
		<td>商品照片:<font color=red><b>*</b></font></td>
		<td>
			<input type="file" name="content">
		</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>
</body>
</html>
<%@ include file="backIndex2.file" %> 