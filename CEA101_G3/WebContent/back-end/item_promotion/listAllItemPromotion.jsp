<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itempromotion.model.*"%>

<%
	ItemPromotionService itemPromotionSvc = new ItemPromotionService();
	List<ItemPromotionVO> list = itemPromotionSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>Insert title here</title>
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


	<table id="table-1">
		<tr>
			<td>
				<h3>所有員工資料 - listAllShopOrder.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back_end/item_promotion/select_page.jsp"><img
						src="<%=request.getContextPath()%>/back_end/item_promotion/images/189585.jpg"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>商品促銷編號</th>
			<th>商品編號</th>
			<th>商品促銷資訊</th>
			<th>商品折扣</th>
			<th>促銷開始時間</th>
			<th>促銷結束時間</th>
		</tr>
		<c:forEach var="itemPromotionVO" items="${list}">
			<tr>
				<td>${itemPromotionVO.item_promotion_id}</td>
				<td>${itemPromotionVO.item_id}</td>
				<td>${itemPromotionVO.item_promotion_info}</td>
				<td>${itemPromotionVO.item_discount}</td>
				<td>${itemPromotionVO.item_prom_start_date}</td>
				<td>${itemPromotionVO.item_prom_close_date}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/item_promotion/item_promotion.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="item_promotion_id"
							value="${itemPromotionVO.item_promotion_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>

				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/item_promotion/item_promotion.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="item_promotion_id" value="${itemPromotionVO.item_promotion_id}">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>