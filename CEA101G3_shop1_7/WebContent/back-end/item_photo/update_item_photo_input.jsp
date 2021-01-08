<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_photo.model.*"%>
<%@ include file="backIndex.file" %> 


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品照片資料修改 </title>
</head>
<body>


<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<%
	ItemPhotoVO itemPhotoVO = (ItemPhotoVO) request.getAttribute("itemPhotoVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/item_photo/itemPhoto.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>商品照片編號:<font color=red><b>*</b></font></td>
		<td><%=itemPhotoVO.getItemPhotoId()%></td>
	</tr>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=itemPhotoVO.getItemId()%></td>
	</tr>
	<tr>
		<td>商品照片:<font color=red><b>*</b></font></td>
		<td>
			<input type="file" name="content">
		</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="itemPhotoId" value="<%= itemPhotoVO.getItemPhotoId()%>">
<input type="hidden" name="itemId" value="<%= itemPhotoVO.getItemId()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
<%@ include file="backIndex2.file" %> 