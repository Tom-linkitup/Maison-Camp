<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='https://fonts.googleapis.com/css?family=Lato:300,400|Montserrat:700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://2-22-4-dot-lead-pages.appspot.com/static/lp918/min/default_thank_you.css">
<script src="https://2-22-4-dot-lead-pages.appspot.com/static/lp918/min/jquery-1.9.1.min.js"></script>
<script src="https://2-22-4-dot-lead-pages.appspot.com/static/lp918/min/html5shiv.js"></script>
<title>Insert title here</title>


<style>
	@import url(//cdnjs.cloudflare.com/ajax/libs/normalize/3.0.1/normalize.min.css);
	@import url(//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css);
</style>
</head>
<body>
	<header class="site-header" id="header">
		<h3 class="site-header__title" data-lead-id="site-header-title" style="font-size:4.25em;">感謝您的訂購!</h3>
	</header>

	<div class="main-content">
		<i class="fa fa-check main-content__checkmark fa-5x" id="checkmark"></i>
		<p class="main-content__body" data-lead-id="main-content-body"><a href="<%=request.getContextPath()%>/front-end/member/Member.jsp" style="text-decoration: none;">回會員中心</a></p>
		<p class="main-content__body" data-lead-id="main-content-body"><a href="<%=request.getContextPath()%>/front-end/front-index.jsp" style="text-decoration: none;">回首頁</a></p>
	</div>

	<footer class="site-footer" id="footer">
		<p class="site-footer__fineprint" id="fineprint">Copyright ©2021 Maison Camp | 露營家 All Rights Reserved</p>
	</footer>
</body>
</html>