<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.room_promotion.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  Room_promotionVO room_promotionVO = (Room_promotionVO) request.getAttribute("room_promotionVO"); //Room_promotionServlet.java(Concroller), �s�Jreq��room_promotionVO����
%>

<html>
<head>
<title>���u��� - listOneRoom_promotion.jsp</title>

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
		 <h3>���u��� - ListOneRoom_promotion.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/room_promotion/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�Ы��u�f�s��</th>
		<th>�Ы�</th>
		<th>�u�f��T</th>
		<th>�u�f�馩</th>
		<th>�u�f�}�l���</th>
		<th>�u�f�������</th>
	</tr>
	<tr>
		<td><%=room_promotionVO.getRoom_promotion_id()%></td>
		<td><%=room_promotionVO.getRoom_category_id()%></td>
		<td><%=room_promotionVO.getRoom_promotion_info()%></td>
		<td><%=room_promotionVO.getRoom_discount()%></td>
		<td><%=room_promotionVO.getRoom_prom_start_date()%></td>
		<td><%=room_promotionVO.getRoom_prom_end_date()%></td>
	</tr>
</table>
</body>
</html>