<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityComment.model.*"%>

<%
	ActivityCommentVO acVO = (ActivityCommentVO) request.getAttribute("activityCommentVO");
%>


<!DOCTYPE html>
<html>
<head>
<title>修改活動評論</title>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="post"
		action="<%=request.getContextPath()%>/actComment/ActCommentServlet.do">
		<table>

			<tr>
				<th>活動評論編號</th>
				<th>活動類別編號</th>
				<th>活動評論內容</th>
				<th>活動評論時間</th>

			</tr>

			<tr>
				<td><input type="text" name="actCommentId"
					value="${activityCommentVO.actCommentId}" readonly></td>

				<td><input type="text" name="actCategoryId"
					value="${activityCommentVO.actCategoryId}" readonly></td>
				<td><input type="text" name="actComment" value=""
					placeholder="請輸入活動評論"></td>
				<td>${activityCommentVO.createTime}</td>
			</tr>
		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="from" value="back-end"> 
		<input type="submit" value="更新評論">
	</form>
	<h4>
		<a href="<%=request.getContextPath()%>/back-end/actComment/actComment_select_page.jsp">回首頁</a>
	</h4>
</body>
</html>