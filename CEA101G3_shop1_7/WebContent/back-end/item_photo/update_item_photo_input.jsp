<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_photo.model.*"%>
<%@ include file="backIndex.file" %> 


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~�Ӥ���ƭק� </title>
</head>
<body>


<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<%
	ItemPhotoVO itemPhotoVO = (ItemPhotoVO) request.getAttribute("itemPhotoVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/item_photo/itemPhoto.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�ӫ~�Ӥ��s��:<font color=red><b>*</b></font></td>
		<td><%=itemPhotoVO.getItemPhotoId()%></td>
	</tr>
	<tr>
		<td>�ӫ~�s��:<font color=red><b>*</b></font></td>
		<td><%=itemPhotoVO.getItemId()%></td>
	</tr>
	<tr>
		<td>�ӫ~�Ӥ�:<font color=red><b>*</b></font></td>
		<td>
			<input type="file" name="content">
		</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="itemPhotoId" value="<%= itemPhotoVO.getItemPhotoId()%>">
<input type="hidden" name="itemId" value="<%= itemPhotoVO.getItemId()%>">
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>
<%@ include file="backIndex2.file" %> 