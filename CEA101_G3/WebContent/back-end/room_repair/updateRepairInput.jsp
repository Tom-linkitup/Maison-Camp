<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.repair.model.*"%>

<%
  RepairVO repairVO = (RepairVO) request.getAttribute("repairVO");
//RepairServlet.java (Concroller) 存入req的repairVO物件 (包括幫忙取出的repairVO, 也包括輸入資料錯誤時的repairVO物件)
%>
<%= repairVO==null %> --${repair.VO.deptno}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_repair_input.jsp</title>

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
		 <h3>資料修改 - update_repair_input.jsp</h3>
		 <h4><a href="/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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

<FORM METHOD="post" ACTION="repair.do" name="form1">
<table>
	<tr>
		<td>維修編號:<font color=red><b>*</b></font></td>
		<td><%=repairVO.getRepair_id()%></td>
	</tr>
	<tr>
		<td>房間編號:</td>
		<td><input type="TEXT" name="room_id" size="45" value="<%=repairVO.getRoom_id()%>" /></td>
	</tr>
	<tr>
		<td>維修資訊:</td>
		<td><input type="TEXT" name="emp_id" size="45"	value="<%=repairVO.getEmp_id()%>" /></td>
	</tr>
		<tr>
		<td>薪水:</td>
		<td><input type="TEXT" name="repair_info" size="45"	value="<%=repairVO.getRepair_info()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="repair_id" value="<%=repairVO.getRepair_id()%>">
<input type="submit" value="送出修改"></FORM>
</body>