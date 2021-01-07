<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityComment.model.*"%>
    
<%
	List<ActivityCommentVO> list = (ArrayList<ActivityCommentVO>) request.getAttribute("list");
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<title>依活動編號查詢評論</title>
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

<table>
	<tr>
		<th>活動評論編號</th>
		<th>活動編號</th>
		<th>活動評論內容</th>
		<th>評論時間</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

	<c:forEach var="activityCommentVO" items="${list}">
		
		<tr>
			<td>${activityCommentVO.actCommentId}</td>
			<td>${activityCommentVO.actCategoryId}</td>
			<td>${activityCommentVO.actComment}</td>
			<td>${activityCommentVO.createTime}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actComment/ActCommentServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="actCommentId"  value="${activityCommentVO.actCommentId}">
			     <input type="hidden" name="from"	value="front-end">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>


<h4><a href="<%=request.getContextPath()%>/back-end/actComment/actComment_select_page.jsp">回首頁</a></h4>
</body>
</html>