<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityComment.model.*"%>


<!DOCTYPE html>
<html>
<head>
<title>活動評論首頁</title>
</head>
<body>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<h3>查詢活動評論</h3>

<p><a href="<%=request.getContextPath()%>/back-end/actComment/listAllActComment.jsp">查詢所有活動評論</a></p>

<h3>依活動評論編號查詢</h3>
<form method="post" action="<%=request.getContextPath()%>/actComment/ActCommentServlet.do">
<input type="text" name="actCommentId" value="" placeholder="請輸入活動評論編號">
<input type="hidden" name="action" value="listByActCommentId">
<input type="submit" value="送出查詢">
</form>

<h3>依活動編號查詢評論</h3>
<form method="post" action="<%=request.getContextPath()%>/actComment/ActCommentServlet.do">
<input type="text" name="actCategoryId" value="" placeholder="請輸入活動類別編號">
<input type="hidden" name="action" value="listByActCategoryId">
<input type="submit" value="送出查詢">
</form>



<h4><a href="<%=request.getContextPath()%>/back-end/actComment/addActComment.jsp">新增活動評論</a></h4>


</body>
</html>