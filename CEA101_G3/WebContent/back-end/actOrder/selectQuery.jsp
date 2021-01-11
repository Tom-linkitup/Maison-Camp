<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityOrder.model.*"%>
<!DOCTYPE html>
<html>
<head>
<title>依條件查詢訂單</title>
</head>
<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div id="content-4">

<h3>依活動訂單編號查詢訂單</h3>
<form method="post" action="<%=request.getContextPath()%>/actOrder/ActOrderServlet.do">
<input type="text" name="actOrderId" value="" placeholder="請輸入活動訂單編號">
<input type="hidden" name="action" value="listByActOrderId">
<input type="hidden" name="from" value="back-end">
<input type="submit" value="送出查詢">
</form>

<h3>依活動編號查詢訂單</h3>
<form method="post" action="<%=request.getContextPath()%>/actOrder/ActOrderServlet.do">
<input type="text" name="actId" value="" placeholder="請輸入活動編號">
<input type="hidden" name="action" value="listByActId">
<input type="hidden" name="from" value="back-end">
<input type="submit" value="送出查詢">
</form>

<h3>依會員編號查詢訂單</h3>
<form method="post" action="<%=request.getContextPath()%>/actOrder/ActOrderServlet.do">
<input type="text" name="memId" value="" placeholder="請輸入會員編號">
<input type="hidden" name="action" value="listByMemId">
<input type="hidden" name="from" value="back-end">
<input type="submit" value="送出查詢">
</form>
</div>

</body>
</html>