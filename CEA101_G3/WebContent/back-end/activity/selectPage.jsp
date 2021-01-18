<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/back-end/back-template/backIndex.file"%>
<html>
<head>
<title>IBM Act: Home</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/activity.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	
</script>
</head>
<body>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div id="container">
		<input class="title" id="tab-1" type="radio" name="tab-group" checked="true"/> 
		<label for="tab-1">查看所有活動</label> 
		<input class="title" id="tab-2" type="radio" name="tab-group" /> 
		<label for="tab-2">新增活動</label> 
		<input class="title" id="tab-3"	type="radio" name="tab-group" /> 
		<label for="tab-3">查詢活動</label> 
		<input class="title" id="tab-4" type="radio" name="tab-group" /> 
		<label for="tab-4">新增活動圖片</label> 
		<input class="title" id="tab-5"	type="radio" name="tab-group" /> 
		<label for="tab-5">查看所有活動圖片</label> 
		<input class="title" id="tab-6"	type="radio" name="tab-group" /> 
		<label for="tab-6">完成活動，訂單待評論</label> 
		
		

		<div id="content">
			<jsp:include page="/back-end/activity/listAllAct.jsp" />
			<jsp:include page="/back-end/activity/addAct.jsp" />
			<jsp:include page="/back-end/activity/selectOne.jsp" />
			<jsp:include page="/back-end/actPhoto/addActPhoto.jsp" />
			<jsp:include page="/back-end/actPhoto/listAllActPhoto.jsp" />
			<jsp:include page="/back-end/activity/completeAct.jsp" />
		</div>
	</div>
		<%@ include file="/back-end/back-template/backIndex2.file"%>
</body>
</html>