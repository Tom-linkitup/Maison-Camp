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
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/activity.css">
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/img/logo.png">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<title>新增活動評論</title>
</head>
<body>
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
     </header>
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