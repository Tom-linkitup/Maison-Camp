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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/activity.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	
</script>
</head>
<body>
	<div id="content-6">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/act.do">
			<h1>統一修改已完成的訂單的狀態</h1>
			<select size="1" name="actId">
			<c:forEach var="actVO" items="${actList}">
				<option value="${actVO.actId}">${actVO.actName}</option>
			</c:forEach>
			
			</select>
			<input type="hidden" name="action" value="completeAct">
			<input type="submit" value="送出">
		</FORM>
	</div>
	
</body>
</html>