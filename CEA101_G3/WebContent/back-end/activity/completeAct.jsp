<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actCategory.model.*"%>
<%
	List<ActivityVO> actList = new ActivityService().getAll();
	pageContext.setAttribute("actList", actList);
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />


</head>
<body>
	<h1>備註:這個頁面 主要是將活動做完成的動作  完成的活動 會將對應的訂單轉換成待評價狀態</h1>
	<div id="content-6">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/act.do">
			<h1>完成的活動</h1>
			<select size="1" name="actId">
			<c:forEach var="actVO" items="${actList}">
				<option value=${actVO.actId}>${actVO.actName}</option>
			</c:forEach>
			<input type="hidden" name="action" value="completeAct">
			<input type="submit" value="送出">
			</select>
		</FORM>
	</div>
</body>
</html>