<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.repair.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  RepairVO repairVO = (RepairVO) request.getAttribute("repairVO"); //RepairServlet.java(Concroller), �s�Jreq��repairVO����
%>

<html>
<head>
<title>�ж���µ��� - listOneRepair.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>��µ��� - ListOneRepair.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/room_repair/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���׽s��</th>
		<th>�ж��s��</th>
		<th>���u�s��</th>
		<th>���׸�T</th>
		
	</tr>
	<tr>
		<td><%=repairVO.getRepair_id()%></td>
		<td><%=repairVO.getRoom_id()%></td>
		<td><%=repairVO.getEmp_id()%></td>
		<td><%=repairVO.getRepair_info()%></td>
	</tr>
</table>

</body>
</html>