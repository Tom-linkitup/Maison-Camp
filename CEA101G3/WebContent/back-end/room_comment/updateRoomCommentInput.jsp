<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room_comment.model.*"%>

<%
Room_commentVO room_commentVO = (Room_commentVO) request.getAttribute("room_commentVO"); 
//Room_commentServlet.java (Concroller) �s�Jreq��room_commentVO���� (�]�A�������X��room_commentVO, �]�]�A��J��ƿ��~�ɪ�room_commentVO����)
%>
<%=room_commentVO==null %> 
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - updateExtraChargesnput.jsp</title>

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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�B�~���O�q��ק� - update_room_comment_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/room_comment/select_page.jsp">
		 <img src="<%=request.getContextPath()%>/back-end/room_comment/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room_comment/room_comment.do" name="form1">
<table>
	<tr>
		<td>�B�~���O�q��s��:<font color=red><b>*</b></font></td>
		<td><%=room_commentVO.getRoom_comment_id()%></td>
	</tr>
	<tr>
		<td>�Ы�:</td>
		<td><input type="TEXT" name="room_category_id" size="45" value="<%=room_commentVO.getRoom_category_id()%>" /></td>
	</tr>
	<tr>
		<td>���פ��e:</td>
		<td><input type="TEXT" name="room_comment_content" size="45"	value="<%=room_commentVO.getRoom_comment_content()%>" /></td>
	</tr>
	
	<tr>
		<td>�ɶ�:</td>
		<td><input type="TEXT" name="time" size="45"	value="<%=room_commentVO.getTime()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="room_comment_id" value="<%=room_commentVO.getRoom_comment_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>






</html>