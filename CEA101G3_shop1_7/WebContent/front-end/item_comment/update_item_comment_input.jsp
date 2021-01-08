<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.item_comment.model.*"%>
<% 
  java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String formatDate = df.format(new java.util.Date());
  Long time = new java.util.Date().getTime();
%>
<%
  ItemCommentVO itemCommentVO = (ItemCommentVO) request.getAttribute("itemCommentVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_item_comment_input.jsp</title>

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
		 <h3>�ӫ~��ƭק� - update_item_comment_input.jsp</h3>
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

<FORM METHOD="post" ACTION="itemComment.do" name="form1">
<table>
	<tr>
		<td>�ӫ~���׽s��:<font color=red><b>*</b></font></td>
		<td><%=itemCommentVO.getItemCommentId()%></td>
	</tr>
	<tr>
		<td>�ӫ~�s��:<font color=red><b>*</b></font></td>
		<td><%=itemCommentVO.getItemId()%></td>
	</tr>
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

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="itemCommentId" value="<%=itemCommentVO.getItemCommentId()%>">
<input type="hidden" name="itemId" value="<%=itemCommentVO.getItemId()%>">
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>