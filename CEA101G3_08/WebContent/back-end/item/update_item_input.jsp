<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>
<%@ include file="backIndex.file" %>
<%
  ItemVO itemVO = (ItemVO) request.getAttribute("itemVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改</title>
</head>
<body bgcolor="white">
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item/item.do" name="form1">
<table>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=itemVO.getItemId()%></td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="itemName"  size="45"
			 value="<%= (itemVO==null)? "羊娃娃" : itemVO.getItemName()%>" /></td>
	</tr>
	<tr>
		<td>商品資訊:</td>
		<td><input type="TEXT" name="itemInfo"  size="45"
			 value="<%= (itemVO==null)? "好可愛" : itemVO.getItemInfo()%>" /></td>
	</tr>
	<tr>
		<td>商品價格:</td>
		<td><input type="TEXT" name="itemPrice" size="45"
			 value="<%= (itemVO==null)? "10000" : itemVO.getItemPrice()%>" /></td>
	</tr>
	<tr>
		<td>商品狀態:</td>
		<td><input type="TEXT" name="itemStatus" size="45"
			 value="<%= (itemVO==null)? "100" : itemVO.getItemStatus()%>" /></td>
	</tr>

	<jsp:useBean id="itemCategorySvc" scope="page" class="com.item_category.model.ItemCategoryService" />
	<tr>
		<td>商品類別:<font color=red><b>*</b></font></td>
		<td><select size="1" name="itemCategoryId">
			<c:forEach var="itemCategoryVO" items="${itemCategorySvc.all}">
				<option value="${itemCategoryVO.itemCategoryId}" ${(itemVO.itemCategoryId==itemCategoryVO.itemCategoryId)? 'selected':'' } >${itemCategoryVO.itemCategoryName}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="itemId" value="<%=itemVO.getItemId()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
<%@ include file="backIndex2.file" %> 