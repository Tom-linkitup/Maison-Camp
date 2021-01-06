<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>
 
<%
  ItemVO itemVO = (ItemVO) request.getAttribute("itemVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~��Ʒs�W - addItem.jsp</title>
</head>
<body>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item/item.do" name="form1">
<div id="content-3">
<table>
	<tr>
		<td>�ӫ~�W��:</td>
		<td><input type="TEXT" name="itemName" size="45"
			 value="<%= (itemVO==null)? "�p�f�f" : itemVO.getItemName()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~��T:</td>
		<td><input type="TEXT" name="itemInfo"  size="45"
			 value="<%= (itemVO==null)? "�n�}�G" : itemVO.getItemInfo()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~����:</td>
		<td><input type="TEXT" name="itemPrice" size="45"
			 value="<%= (itemVO==null)? "10000" : itemVO.getItemPrice()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~���A:</td>
		<td><input type="TEXT" name="itemStatus" size="45"
			 value="<%= (itemVO==null)? "1" : itemVO.getItemStatus()%>" /></td>
	</tr>

	<jsp:useBean id="ItemCategorySvc" scope="page" class="com.item_category.model.ItemCategoryService" />
	<tr>
		<td>�ӫ~���O:<font color=red><b>*</b></font></td>
		<td><select size="1" name="itemCategoryId">
			<c:forEach var="itemCategoryVO" items="${ItemCategorySvc.all}">
				<option value="${itemCategoryVO.itemCategoryId}" ${(itemVO.itemCategoryId==itemCategoryVO.itemCategoryId)? 'selected':'' } >${itemCategoryVO.itemCategoryName}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W">
</div>
</FORM>
</body>
</html>