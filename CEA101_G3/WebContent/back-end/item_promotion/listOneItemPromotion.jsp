<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ page import="com.itempromotion.model.*"%>
<%@page import="org.json.JSONObject"%>

<%
	ItemPromotionVO itemPromotionVO = (ItemPromotionVO) request.getAttribute("itemPromotionVO");
%>
<html>
<head>
<title>show one item_promotion</title>

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

	<table id="table-1">
		<tr>
			<td>
				<h3>���u��� - ListOneEmp.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back_end/item_promotion/select_page.jsp"><img
						src="<%=request.getContextPath()%>/back_end/item_promotion/images/189585.jpg"
						width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�ӫ~�P�P�s��</th>
			<th>�ӫ~�s��</th>
			<th>�ӫ~�P�P��T</th>
			<th>�ӫ~�馩</th>
			<th>�P�P�}�l���</th>
			<th>�P�P�������</th>
		</tr>
		<tr>
			<td><%=itemPromotionVO.getItem_promotion_id()%></td>
			<td><%=itemPromotionVO.getItem_id()%></td>
			<td><%=itemPromotionVO.getItem_promotion_info()%></td>
			<td><%=itemPromotionVO.getItem_discount()%></td>
			<td><%=itemPromotionVO.getItem_prom_start_date()%></td>
			<td><%=itemPromotionVO.getItem_prom_close_date()%></td>
		</tr>
	</table>

</body>
</html>
