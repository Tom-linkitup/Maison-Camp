<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_category.model.*"%>
<%@ include file="backIndex.file" %> 
<%
ItemCategoryVO itemCategoryVO = (ItemCategoryVO) request.getAttribute("itemCategoryVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料新增 - addItem.jsp</title>

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
		 <h3>商品資料新增 - addItemCategory.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath() %>/back-end/item_category/select_page.jsp"><img src="<%=request.getContextPath() %>/images/1.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

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

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/item_category/itemCategory.do" name="form1">
<table>
	<tr>
		<td>商品類別編號:</td>
		<td><input type="TEXT" name="itemCategoryId" size="45"
			 value="<%= (itemCategoryVO==null)? "IXXX" : itemCategoryVO.getItemCategoryId()%>" /></td>
	</tr>
	<tr>
		<td>商品類別名稱:</td>
		<td><input type="TEXT" name="itemCategoeyName"  size="45"
			 value="<%= (itemCategoryVO==null)? "精品類" : itemCategoryVO.getItemCategoryName()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>
</html>
<%@ include file="backIndex2.file" %> 