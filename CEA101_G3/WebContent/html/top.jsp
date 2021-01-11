<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/style.css">
<link rel="shortcut icon" type="image/png"
	href="/CEA101G3/img/camplogo.png">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css" />	
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

<title>Maison Camp | 露營家</title>
</head>

<body>
	<header class="header">
		<nav role="navigation">
			<div id="menuToggle">
				<input type="checkbox" id="checkboxtoggle" /> <span></span> <span></span>
				<span></span>
				<ul id="menu">
					<a href="#">
						<li>首頁</li>
					</a>
					<a href="#">
						<li>最新消息</li>
					</a>
					<a href="#">
						<li>會員登入</li>
					</a>
					<a href="#">
						<li>立即訂房</li>
					</a>
					<a href="#">
						<li>精選商城</li>
					</a>
					<a href="#">
						<li>活動預約</li>
					</a>
					<a href="#">
						<li>聯絡我們</li>
					</a>
				</ul>
			</div>
		</nav>
		<div class="col-xs-4 col-12 logo">
			<a href="<%=request.getContextPath()%>/front-end/activity/selectPage.jsp"><img id="logoo" class="img-logo" 	src="/CEA101G3/img/logo.png" alt=""></a>
			<ul class="signin-links">
	        	<li><i style="margin-right:7px; color:#c15c16;" class="fas fa-child fa-1x"></i>${memVO.name} 您好<i style="color:#496b6b; margin: 0 10px 0 5px;" class="fas fa-exclamation"></i><a class="signin" href="<%=request.getContextPath()%>/Member.do?action=logout"><i class="fas fa-sign-out-alt"></i></a></li>
	      	</ul>
		</div>
	</header>
	<section>
		<div id="carouselExampleControls" class="carousel slide lickme"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<a href=""><img src="<%=request.getContextPath()%>/img/slide2.png"
						class="d-block w-100" alt="..."></a>
				</div>
				<div class="carousel-item">
					<a href=""><img src="<%=request.getContextPath()%>/img/slide1.jpg"
						class="d-block w-100" alt="..."></a>
				</div>
				<div class="carousel-item">
					<a href=""><img src="<%=request.getContextPath()%>/img/slide3.jpg"
						class="d-block w-100" alt="..."></a>
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleControls"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleControls"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</section>
</body>
</html>