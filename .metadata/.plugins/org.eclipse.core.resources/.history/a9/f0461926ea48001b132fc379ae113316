<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<span>請至您的信箱確認驗證碼：</span>
        
        <form action="<%=request.getContextPath()%>/Verify.do" method="post">
            <input type="text" name="authcode" >
            <input type="submit" value="verify">
        </form>
        
        <h4 class="success"></h4>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<c:if test="${authSuccess == 'yes'}">
	<script>
		$(".success").text("驗證成功")
	</script>
</c:if>
</body>
</html>