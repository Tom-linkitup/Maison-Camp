<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_category.model.*"%>
<%@ include file="backIndex.file" %> 
<%
  ItemCategoryVO itemCategoryVO = (ItemCategoryVO) request.getAttribute("itemCategoryVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_item_category_input.jsp</title>

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
		 <h4><a href="<%=request.getContextPath() %>/back-end/iem_category/select_page.jsp"><img src="<%=request.getContextPath() %>/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="itemCategory.do" name="form1">
<table>
	<tr>
		<td>�ӫ~���O�s��:<font color=red><b>*</b></font></td>
		<td><%=itemCategoryVO.getItemCategoryId()%></td>
	</tr>
	<tr>
		<td>�ӫ~���O�W��:</td>
		<td><input type="TEXT" name="itemCategoryName"  size="45"
			 value="<%= (itemCategoryVO==null)? "��~��" : itemCategoryVO.getItemCategoryName()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="itemCategoryId" value="<%=itemCategoryVO.getItemCategoryId()%>">
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>
<%@ include file="backIndex2.file" %> 