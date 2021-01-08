<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_comment.model.*"%>
<%@ include file="backIndex.file" %> 

<% 
  java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String formatDate = df.format(new java.util.Date());
  Long time = new java.util.Date().getTime();
%>
<%
  ItemCommentVO itemCommentVO = (ItemCommentVO) request.getAttribute("itemCommentVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~��Ʒs�W - addItem.jsp</title>

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
		 <h3>�ӫ~���׷s�W - addItemComment.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back-end/item_comment/select_page.jsp"><img src="images/1.jpg" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/item_comment/itemComment.do" name="form1">
<table>
	<tr>
		<td>�ӫ~����:</td>
		<td><input type="TEXT" name="shopComment" size="45" maxlength="200"
			 value="<%= (itemCommentVO==null)? "�o�F��n��!" : itemCommentVO.getShopComment()%>" /></td>
	</tr>
	
     
	<jsp:useBean id="now" scope="page" class="java.util.Date" /> 
	<tr>
		<td>���׮ɶ�(�t�ήɶ�):</td>
		<td><input type="TEXT" name="timimg" size="45" disabled="disabled"
			 value="<%=formatDate%>" /></td>
		<td><input type="hidden" name="time" size="45" 
			 value="<%=time%>" /></td>
	</tr>

	<jsp:useBean id="ItemSvc" scope="page" class="com.item.model.ItemService" />
	<tr>
		<td>�ӫ~���O:<font color=red><b>*</b></font></td>
		<td><select size="1" name="itemId">
			<c:forEach var="itemVO" items="${ItemSvc.all}">
				<option value="${itemVO.itemId}" ${(itemCommentVO.itemId==itemVO.itemId)? 'selected':'' } >${itemVO.itemName}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>
</html>
<%@ include file="backIndex2.file" %> 