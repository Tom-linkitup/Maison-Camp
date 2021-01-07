<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actPhoto.model.*"%>


<!DOCTYPE html>
<html>
<head>
<title>ActPhoto_select</title>
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


<h3>查詢圖片</h3>

<p><a href="<%=request.getContextPath()%>/back-end/actPhoto/listAllActPhoto.jsp">查詢所有圖片</a></p>


<h3>依圖片編號查詢</h3>
<form method="post" action="<%= request.getContextPath()%>/actPhoto/ActPhotoServlet.do">
<input type="text" name="ACT_PHOTO_ID" value="" placeholder="請輸入活動圖片編號">
<input type="hidden" name="action" value="listByActPhotoId">
<input type="submit" value="送出查詢">
</form>

<h3>依活動編號查詢</h3>
<form method="post" action="<%= request.getContextPath()%>/actPhoto/ActPhotoServlet.do">
<input type="text" name="ACT_ID" value="" placeholder="請輸入活動編號">
<input type="hidden" name="action" value="listByActId">
<input type="submit" value="送出查詢">
</form>

<h4><a href="<%=request.getContextPath()%>/back-end/actPhoto/addActPhoto.jsp">新增圖片</a></h4>


</body>
</html>