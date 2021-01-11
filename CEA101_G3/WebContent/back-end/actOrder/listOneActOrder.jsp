<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityOrder.model.*"%>

<%
	ActivityOrderVO aoVO = (ActivityOrderVO) request.getAttribute("activityOrderVO");
%>

<!DOCTYPE html>
<html>
<head>
<title>單筆活動訂單查詢</title>
</head>
<body>
<h4><a href="<%=request.getContextPath()%>/back-end/actOrder/actOrder_select_page.jsp">回首頁</a></h4>
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
			<th>活動訂單編號</th>
			<th>活動編號</th>
			<th>會員編號</th>
			<th>備註</th>
			<th>人數</th>
			<th>付款方式</th>
			<th>訂單成立時間</th>
			<th>活動價格</th>
			<th>狀態碼</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>

		<tr>
			<td>${activityOrderVO.actOrderId}</td>
			<td>${activityOrderVO.actId}</td>
			<td>${activityOrderVO.memId}</td>
			<td>${activityOrderVO.note}</td>
			<td>${activityOrderVO.people}</td>
			<td>${activityOrderVO.payment}</td>
			<td>${activityOrderVO.createTime}</td>
			<td>${activityOrderVO.actPrice}</td>
			<td>${activityOrderVO.status}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actOrder/ActOrderServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="actOrderId"  value="${activityOrderVO.actOrderId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actOrder/ActOrderServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="actOrderId"  value="${activityOrderVO.actOrderId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</table>


</body>
</html>