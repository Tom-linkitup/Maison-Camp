<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item.model.*"%>

<%
  ItemVO itemVO = (ItemVO) request.getAttribute("itemVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_item_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>
<body bgcolor="white">

<table id="table-1">
	<tr><td>
		 <h3>�ӫ~��ƭק� - update_item_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

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

<FORM METHOD="post" ACTION="item.do" name="form1">
<table>
	<tr>
		<td>�ӫ~�s��:<font color=red><b>*</b></font></td>
		<td><%=itemVO.getItemId()%></td>
	</tr>
	<tr>
		<td>�ӫ~�W��:</td>
		<td><input type="TEXT" name="itemName"  size="45"
			 value="<%= (itemVO==null)? "�ϫ���" : itemVO.getItemName()%>" /></td>
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
			 value="<%= (itemVO==null)? "100" : itemVO.getItemStatus()%>" /></td>
	</tr>

	<jsp:useBean id="itemCategorySvc" scope="page" class="com.item_category.model.ItemCategoryService" />
	<tr>
		<td>�ӫ~���O:<font color=red><b>*</b></font></td>
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
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>