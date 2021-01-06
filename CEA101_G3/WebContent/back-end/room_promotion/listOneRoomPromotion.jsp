<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.room_promotion.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Room_promotionVO room_promotionVO = (Room_promotionVO) request.getAttribute("room_promotionVO"); //Room_promotionServlet.java(Concroller), 存入req的room_promotionVO物件
%>

<html>
<head>
<title>員工資料 - listOneRoom_promotion.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneRoom_promotion.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/room_promotion/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>房型優惠編號</th>
		<th>房型</th>
		<th>優惠資訊</th>
		<th>優惠折扣</th>
		<th>優惠開始日期</th>
		<th>優惠結束日期</th>
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