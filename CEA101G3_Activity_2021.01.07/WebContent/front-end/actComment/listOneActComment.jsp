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
<title>依評論編號查詢</title>
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


	<table>
		<tr>
			<th>活動評論編號</th>
			<th>活動編號</th>
			<th>活動評論內容</th>
			<th>評論時間</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>



		<tr>
			<td>${activityCommentVO.actCommentId}</td>
			<td>${activityCommentVO.actCategoryId}</td>
			<td>${activityCommentVO.actComment}</td>
			<td>${activityCommentVO.createTime}</td>

			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actComment/ActCommentServlet.do"
					style="margin-bottom: 0px;">
					<input type="hidden" name="action" value="getOne_For_Update">
					<input type="hidden" name="from" value="back-end">
					<input type="submit" value="修改"> <input type="hidden" name="actCommentId" value="${activityCommentVO.actCommentId}">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actComment/ActCommentServlet.do"
					style="margin-bottom: 0px;">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="from" value="back-end">
					<input type="submit" value="刪除"> <input type="hidden" name="actCommentId" value="${activityCommentVO.actCommentId}">
				</FORM>
			</td>
		</tr>

	</table>



<h4><a href="<%=request.getContextPath()%>/back-end/actComment/actComment_select_page.jsp">回首頁</a></h4>
</body>
</html>