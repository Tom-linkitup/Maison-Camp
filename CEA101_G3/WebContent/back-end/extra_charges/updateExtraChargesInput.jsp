<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.extra_charges.model.*"%>

<%
Extra_chargesVO extra_chargesVO = (Extra_chargesVO) request.getAttribute("extra_chargesVO"); 
//Extra_chargesServlet.java (Concroller) �s�Jreq��extra_chargesVO���� (�]�A�������X��extra_chargesVO, �]�]�A��J��ƿ��~�ɪ�extra_chargesVO����)
%>
<%= extra_chargesVO==null %> 
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
		 <h3>�B�~���O�q��ק� - update_extra_charges_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/room_repair/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/room_repair/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="extra_charges.do" name="form1">
<table>
	<tr>
		<td>�B�~���O�q��s��:<font color=red><b>*</b></font></td>
		<td><%=extra_chargesVO.getExtra_charges_id()%></td>
	</tr>
	<tr>
		<td>�q�Эq��s��:</td>
		<td><input type="TEXT" name="room_order_id" size="45" value="<%=extra_chargesVO.getRoom_order_id()%>" /></td>
	</tr>
	<tr>
		<td>���O���e:</td>
		<td><input type="TEXT" name="item" size="45"	value="<%=extra_chargesVO.getItem()%>" /></td>
	</tr>
	
	<tr>
		<td>����:</td>
		<td><input type="TEXT" name="price" size="45"	value="<%=extra_chargesVO.getPrice()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="extra_charges_id" value="<%=extra_chargesVO.getExtra_charges_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>






</html>