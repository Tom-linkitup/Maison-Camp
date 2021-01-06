<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title>login - Bootsnipp.com</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back-end/emp_login.css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
</head>
<body>


</head>
<body>
<div class="wrapper">

	<div class="container">
		<h1>Welcome</h1>
		<c:if test="${not empty errorMsgs}">
			<c:forEach var="message" items="${errorMsgs}">
				<p>${message}</p>
			</c:forEach>
		</c:if>
		<form method="post" class="form" action="<%=request.getContextPath()%>/loginhandler">
			<input type="text" placeholder="username" name="emp_user_id" >
			<input type="password" placeholder="password" name="emp_user_pwd" >
			<input type="hidden" name="action" value="loginEmp">
			<button type="submit" id="login-button">Login</button>
			
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>

<%
	session.removeAttribute("errorMsgs");
%>
<script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script > $("#login-button").click(function(){
// 		 event.preventDefault();
		 
// 		 $('form').fadeOut(500);
// 		 $('.wrapper').addClass('form-success');
});
//# sourceURL=pen.js
</script>
</body>
</html>

