<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<%@ include file="/back-end/back-template/backIndex.file"%>
<html>
<head>
<title>act comment selectPage</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/actcomment.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	
</script>
</head>
<body>





<div id="container">
		<div id="error">
			<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
		
		</div>



		<input class="title" id="tab-1" type="radio" name="tab-group" checked="true"/> 
		<label for="tab-1">�d�ݩҦ�����</label> 
		<input class="title" id="tab-2" type="radio" name="tab-group" /> 
		<label for="tab-2">�̬������O�d�ߵ���</label> 
		<input class="title" id="tab-3"	type="radio" name="tab-group" /> 
		<label for="tab-3">�d�߳�@���ʵ���</label> 
		<input class="title" id="tab-4"	type="radio" name="tab-group" /> 
		<label for="tab-4">�s�W���ʵ���</label> 
		

		<div id="content">
			<jsp:include page="/back-end/actComment/listAllActComment.jsp" />
			<jsp:include page="/back-end/actComment/listCommentByActCategoryId.jsp" />
			<jsp:include page="/back-end/actComment/listOneActComment.jsp" />
			<jsp:include page="/back-end/actComment/addActComment.jsp" />
		</div>
</div>
		<%@ include file="/back-end/back-template/backIndex2.file"%>
</body>
</html>