<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@page import="org.json.JSONObject"%>
<%@ page import="com.shop_order.model.*"%>
<%
	String shop_order_id = request.getParameter("shop_order_id");
	ShopOrderService svc = new ShopOrderService();
	ShopOrderVO vo = svc.getOneShopOrder(shop_order_id);
%>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<table>
	 	<tr>
			<th>訂單編號</th>
			<th>員工編號</th>
			<th>付款方式</th>
			<th>創造時間</th>
			<th>商品訂單總金額</th>
			<th>商品訂單狀態</th>
		</tr>
		<tr>
			<td><%=vo.getShop_order_id() %></td>
			<td><%=vo.getMem_id() %></td>
			<td><%=vo.getPayment() %></td>
			<td><%=vo.getTime()%></td>
			<td><%=vo.getShop_total_amount()%></td>
			<td><%=vo.getStatus()%></td>
		</tr>		
	</table>
</body>
</html>