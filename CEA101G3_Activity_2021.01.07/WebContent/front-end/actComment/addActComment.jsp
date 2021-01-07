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
<title>新稱活動編號</title>
</head>
<body>
	<jsp:include page="/html/top.jsp" flush="true" />
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="<%=request.getContextPath()%>/actComment/ActCommentServlet.do">
<input type="text" name="actCategoryId" value="" placeholder="請輸入活動類別編號">
<input type="text" name="actComment" value="" placeholder="請輸入活動評論">
<input type="hidden" name="action" value="insert">
<input type="hidden" name="from" value="front-end">
<input type="submit" value="新增評論">
</form>



<h4><a href="<%=request.getContextPath()%>/back-end/actComment/actComment_select_page.jsp">回首頁</a></h4>
	<jsp:include page="/html/footer.jsp" flush="true" />
</body>
</html>