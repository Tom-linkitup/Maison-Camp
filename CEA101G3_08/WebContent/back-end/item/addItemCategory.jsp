<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_category.model.*"%> 
<%
ItemCategoryVO itemCategoryVO = (ItemCategoryVO) request.getAttribute("itemCategoryVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~��Ʒs�W - addItem.jsp</title>
</head>
<body bgcolor="white">

<table id="table-1">
	<tr><td>
		 <h3>�ӫ~��Ʒs�W - addItemCategory.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath() %>/back-end/item_category/select_page.jsp"><img src="<%=request.getContextPath() %>/images/1.jpg" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/item_category/itemCategory.do" name="form1">
<table>
	<tr>
		<td>�ӫ~���O�s��:</td>
		<td><input type="TEXT" name="itemCategoryId" size="45"
			 value="<%= (itemCategoryVO==null)? "IXXX" : itemCategoryVO.getItemCategoryId()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~���O�W��:</td>
		<td><input type="TEXT" name="itemCategoeyName"  size="45"
			 value="<%= (itemCategoryVO==null)? "��~��" : itemCategoryVO.getItemCategoryName()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>
</html>