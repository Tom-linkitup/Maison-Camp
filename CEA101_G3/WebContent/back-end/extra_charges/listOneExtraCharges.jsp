<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.extra_charges.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
Extra_chargesVO extra_chargesVO = (Extra_chargesVO) request.getAttribute("extra_chargesVO"); //Extra_chargesServlet.java(Concroller), �s�Jreq��extra_chargesVO����
%>


<html>
<head>
<title>�B�~���O��� - listOneExtra_charges.jsp</title>

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
	width: 800px;
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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listOneExtraCharges.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/room_repair/select_page.jsp">
		 <img src="<%=request.getContextPath()%>/back-end/room_repair/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>�B�~���O�s��</th>
		<th>�q�ЧǸ�</th>
		<th>���O���e</th>
		<th>����</th>
		
	</tr>

		<tr>
			<td><%=extra_chargesVO.getExtra_charges_id()%></td>
			<td><%=extra_chargesVO.getRoom_order_id()%></td>
			<td><%=extra_chargesVO.getItem()%></td>
			<td><%=extra_chargesVO.getPrice()%></td>
			
		
		</tr>
	
</table>


</body>
</html>