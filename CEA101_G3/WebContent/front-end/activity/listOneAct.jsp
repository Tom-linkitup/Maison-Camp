<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.activityOrder.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO"); 
%>

<jsp:useBean id="actOrder" scope="page" class="com.activityOrder.model.ActivityOrderService" />

<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/activity.css">
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/img/logo.png">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<title>活動資料 - listOneAct.jsp</title>

<style>
div.test {
	display: inline-block;
	margin: auto 20px;
	vertical-align: top;
}

div.test h3 {
	text-align: center;
}

input#addOrder {
	text-align: center;
	margin: 0px auto;
}

div.demo {
	border: 1px solid blue;
	padding: 20px;
	position:relative;
	background-color:#f9f9f9;
}

div.comment {
	height: 700px;
	overflow: auto;
}

#float-sidebar {
	position: fixed;
	left: 90%;
	top: 32%;
	width: 80px;
	text-align: center;
	z-index: 99999;
}

#float-fb {
	width: auto;
	height: 80px;
	margin-bottom: 10px;
	background-color: #5a6c91;
	padding-top: 7px;
	border-radius: 50%;
}

#float-fb i {
	color: #fff;
	font-size: 35px;
}

#float-fb a {
	color: #fff;
	font-size: 15px;
	text-decoration: none;
}

#float-fb i:hover {
	opacity: 0.5;
}

#float-fb a:hover {
	opacity: 0.5;
}

#float-booking {
	width: auto;
	height: 80px;
	margin-bottom: 10px;
	background-color: #d17d7f;
	padding-top: 7px;
	border-radius: 50%;
}

#float-booking i {
	color: #fff;
	font-size: 35px;
}

#float-booking a {
	color: #fff;
	font-size: 15px;
	text-decoration: none;
}

#float-booking i:hover {
	opacity: 0.5;
}

#float-booking a:hover {
	opacity: 0.5;
}

#float-top {
	width: auto;
	height: 80px;
	margin-bottom: 10px;
	padding-top: 10px;
}

#float-top i {
	color: #c15c61;
	font-size: 35px;
}

#float-top a {
	color: #c15c61;
	font-size: 15px;
	text-decoration: none;
}

@media screen and (max-width:767px) {
	#float-sidebar {
		display: none !important;
	}
}
</style>

</head>
<body bgcolor='white'>
	<header class="header">
         <nav role="navigation">
             <div id="menuToggle">
                 <input type="checkbox" id="checkboxtoggle"/>
                 <span></span>
                 <span></span>
                 <span></span>
                 <ul id="menu">
                        <a href="<%=request.getContextPath()%>/front-end/front-index.jsp"><li>首頁</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/news/News.jsp"><li>最新消息</li></a>
                        <a class="enterAlert" href="<%=request.getContextPath()%>/front-end/member/Member.jsp"><li>會員中心</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/room-type/RoomType.jsp"><li>帳型介紹</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp"><li>立即訂房</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/item/shoppingMall.jsp"><li>精選商城</li></a>
                        <a href="<%=request.getContextPath()%>/front-end/activity/selectPage.jsp"><li>活動預約</li></a>
                        <a href="#"><li>聯絡我們</li></a>
                    </ul>
             </div>
         </nav>          
         <a href="#"><img id="logoo" class="img-logo" src="<%=request.getContextPath()%>/img/logo.png" alt=""></a>      
         <ul class="signin-links">
	       	<li><i style="margin-right:7px; color:#c15c16;" class="fas fa-child fa-1x"></i>${memVO.name} 您好<i style="color:#496b6b; margin: 0 10px 0 5px;" class="fas fa-exclamation"></i><a class="signin" href="<%=request.getContextPath()%>/Member.do?action=logout"><i class="fas fa-sign-out-alt"></i></a></li>
	     </ul>   
     </header>
	<!-- 左邊活動資訊 -->
	<c:set value="0" var="sum" />
		<c:forEach var="actOrderVO" items="${actOrder.findByActId(activityVO.actId)}">
			<c:if test="${actOrderVO.status==0}">
			<c:set value="${sum + actOrderVO.people}" var="sum" />
			</c:if>
		</c:forEach>
	
	<div class="justify-content-start demo">
		<div class="col-6 test">

			<h3 class="text-primary"><%=activityVO.getActName()%></h3>

			<div class="row">
				<div class="col-xs-12 small">
					<p>
						<i class="fas fa-calendar-alt"></i>活動起始日期：<%=activityVO.getActStartDate()%>
					</p>
					<p>
						<i class="fas fa-calendar-alt"></i>活動結束日期：<%=activityVO.getActStartDate()%>
					</p>
					<p>
						<i class="fas fa-dollar-sign"></i>活動價格:<%=activityVO.getActPrice()%>
					</p>
					<p>
						<i class="fas fa-map-marker-alt"></i>地點：露營區內
					</p>

				</div>
			</div>

			<!-- 簡介  -->
			<div class="row enr-margin-top enr-margin-bottom">
				<div class="col-xs-12">
					<h4>簡介∕</h4>
					<p><%=activityVO.getActInfo()%></p>
					<div class="well-xs small"></div>
				</div>
			</div>
			<hr>
			<!-- 報名資訊 !-->
			<div class="row enr-margin-top enr-margin-bottom">
				<div class="col-xs-12">
					<h4>報名資訊∕</h4>
					<div class="text-info small">
						<p>
							<i class="fas fa-user-check"></i>已報名人數:${sum}
						</p>
						<p>
							<i class="fas fa-user"></i>報名人數下限:<%=activityVO.getMinPeople()%>
						</p>
						<p>
							<i class="fas fa-users"></i>報名人數上限:<%=activityVO.getMaxPeople()%>
						</p>
						<p>
							<i class="fas fa-calendar-alt"></i>活動開始報名時間：<%=activityVO.getActApplyOpen()%>
						</p>
						<p>
							<i class="fas fa-calendar-alt"></i>活動結束報名日期：<%=activityVO.getActApplyClose()%>
						</p>
						<p>
					</div>
				</div>
			</div>
			<!-- 優惠資訊 !-->
			<div class="row enr-margin-top enr-margin-bottom">
				<div class="col-xs-12">
					<h4>優惠資訊∕</h4>
					<div class="text-info small">
						<p>
							<i class="fas fa-tags"></i>活動折扣:<%=(activityVO.getActDiscount() == 1) ? "無折扣" : activityVO.getActDiscount()%>
						</p>
						<p>
							<i class="fas fa-tags"></i>折扣內容:<%=activityVO.getActPromInfo()%>
						</p>
						<p>
							<i class="fas fa-tags"></i>折扣開始時間:<%=activityVO.getActPromStartDate()%>
						</p>
						<p>
							<i class="fas fa-tags"></i>折扣結束時間:<%=activityVO.getActPromCloseDate()%>
						</p>
					</div>
				</div>
			</div>
			<div class="row enr-margin-top enr-margin-bottom">
			<c:choose>
			<c:when test="${sum >= activityVO.maxPeople}">
				<input id="addOrder" type="button" value="已額滿" disabled>
				</div>
			</c:when>
			<c:otherwise>
				<input id="addOrder" type="button" value="我要報名" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/front-end/actOrder/addActOrder.jsp?actId=<%=activityVO.getActId()%>'">
				</div>
			</c:otherwise>
			
				</c:choose>	    
		</div>
		<!-- 右邊評論 -->
		<div class="col-5 test comment">
			<h3 class="text-primary">活動評論</h3>
			<jsp:useBean id="actCommentSvc" scope="page" class="com.activityComment.model.ActivityCommentService" />
			<c:forEach var="actCommentVO" items="${actCommentSvc.getByActCategoryId(activityVO.actCategoryId)}">
				<c:if test="${not empty actCommentVO.actComment}">
					<div>
						<p>
							<i class="fas fa-comment-dots"></i>${actCommentVO.actComment}</p>
					</div>
					<hr>
				</c:if>
			</c:forEach>
		</div>
					<!-- float-sidebar -->
		<div id="float-sidebar">
			<div id="float-booking">
				<a href="<%= request.getContextPath()%>/front-end/activity/selectPage.jsp"> <i class="fas fa-home"></i><br> 活動列表</a>
			</div>
			<div id="float-top">
				<a href="#"> <i class="fa fa-chevron-up"></i><br> Top</a>
			</div>
		</div>
	</div>
	<footer class="site-footer">
            <div class="container">
                <div class="row">
                <div class="col-sm-12 col-md-6">
                    <h6>關於我們</h6>
                    <p class="text-justify"><i>Maison Camp</i> 豪華露營提供多項的活動的安排與遊憩設施。<br>適合親子或三五好友一同前來放鬆體驗。<br>豪華露營、 野奢莊園 、野奢酒店的概念，要帶給您一種全新的露營體驗。<br>快來與我們一起聽流水蟲鳴鳥叫 與森林一同呼吸。</p>
                </div>
                <div class="col-xs-6 col-md-2">
                    <h6>快速連結</h6>
                    <ul class="footer-links">
                    <li><a href="">會員登入</a></li>
                    <li><a href="">立即訂房</a></li>
                    <li><a href="">精選商城</a></li>
                    <li><a href="">預約活動</a></li>
                    <li><a href="">聯繫我們</a></li>
                    </ul>
                </div>
                <div class="col-xs-6 col-md-3">
                    <a href=""><img class="footer-map" src="<%=request.getContextPath()%>/img/footer.png" style="height: 160px; width: 300px;" alt=""></a>
                </div>
                </div>
                <hr>
            </div>
            <div class="container">
                <div class="row">
                <div class="col-md-8 col-sm-6 col-xs-12">
                    <p class="copyright-text">Copyright &copy; 2021 All Rights Reserved by 
                <a href="#">Maison Camp</a>.
                    </p>
                </div>
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <ul class="social-icons">
                    <li><a class="facebook" href="#"><i class="fab fa-facebook-f"></i></a></li>
                    <li><a class="twitter" href="#"><i class="fab fa-twitter"></i></a></li>
                    <li><a class="dribbble" href="#"><i class="fab fa-dribbble"></i></a></li>
                    <li><a class="linkedin" href="#"><i class="fab fa-linkedin"></i></a></li>   
                    </ul>
                </div>
                </div>
            </div>
        </footer>
      <!-- footer -->
        <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>