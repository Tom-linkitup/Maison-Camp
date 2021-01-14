<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityComment.model.*"%>

<%
	List<ActivityCommentVO> list = (ArrayList<ActivityCommentVO>) request.getAttribute("list");
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<title>依活動類別查詢評論</title>
</head>
<body>
<div id="content-2">
	<div>
	<jsp:useBean id="acSvc" class="com.actCategory.model.ActCategoryService" />
		<form  METHOD="post" ACTION="<%=request.getContextPath()%>/actComment/ActCommentServlet.do">
			<select size="1" name="actCategoryId">
				<c:forEach var="acVO" items="${acSvc.all}">
					<option value="${acVO.actCategoryId}">${acVO.actCategoryId}
				</c:forEach>
			</select>
			
			<input type="hidden" name="action" value="listByActCategoryId"> 
			<input type="submit" value="送出查詢">
		</form>
	</div>

		<c:if test="${not empty list}">
		<table>
			<tr>
				<th>活動評論編號</th>
				<th>活動訂單編號</th>
				<th>活動編號</th>
				<th>活動評論內容</th>
				<th>評論時間</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>

			<c:forEach var="activityCommentVO" items="${list}">

				<tr>
					<td>${activityCommentVO.actCommentId}</td>
					<td>${activityCommentVO.actOrderId}</td>
					<td>${activityCommentVO.actCategoryId}</td>
					<td>${activityCommentVO.actComment}</td>
					<td>${activityCommentVO.createTime}</td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/actComment/ActCommentServlet.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="actCommentId" value="${activityCommentVO.actCommentId}">
							<input type="hidden" name="from" value="back-end"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/actComment/ActCommentServlet.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> <input type="hidden"
								name="actCommentId" value="${activityCommentVO.actCommentId}">
							<input type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
</div>
</body>
</html>