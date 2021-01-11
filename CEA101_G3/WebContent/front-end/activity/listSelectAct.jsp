<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	List<ActivityVO> list = (List) request.getAttribute("activityList"); //EmpServlet.java(Concroller), 存入req的empVO物件
	pageContext.setAttribute("list", list);

%>

<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/activity.css">
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/img/logo.png">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<title>活動資料 - listSelectAct.jsp</title>

<style>

div#demo {
	text-align: center;
	background-color:#f9f9f9;
	min-height:1400px;
}

div.list {
	border: 1px solid blue;
	border-radius: 3em;
	margin: 20px;
}

div.left {
	display: inline-block;
	margin: auto;
	width: 30%;
	vertical-align: middle;
}

div.right {
	display: inline-block;
	margin: auto 20px;
	width: 50%;
	vertical-align: middle;
}

div.sub {
	display: inline-block;
	margin: auto 20px;
	width: 10%;
	vertical-align: middle;
}

div.title {
	text-align: center;
	width: auto;
	height: auto;
	margin: 0px;
}

div.content {
	margin: 0px;
	width: auto;
	height: auto;
}

div.left img {
	border-radius: 50%;
	max-width: 100%;
	filter: drop-shadow(5px 5px 5px #333);
}

h1 {
	margin: 0px;
}
div.search {
	width: 100%;
	left: 20%;
	background-color: #ea5514;
	display: inline-block;
}

div.formGroup {
	display: inline-block;
	left: 15px;
	margin: 0px;
	padding: 0px;
	vertical-align: bottom;
}

form.myForm {
	vertical-align: middle;
	display: inline-block;
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
                 	<a href="<%=request.getContextPath()%>/front-end/member/Member.jsp"><li>會員中心</li></a>
                 	<a href="<%=request.getContextPath()%>/front-end/room-type/RoomType.jsp"><li>帳型介紹</li></a>
                 	<a href="<%=request.getContextPath()%>/front-end/room-booking/RoomBooking.jsp"><li>立即訂房</li></a>
                 	<a href="#"><li>精選商城</li></a>
                 	<a href="#"><li>活動預約</li></a>
                 	<a href="#"><li>聯絡我們</li></a>
                 </ul>
             </div>
         </nav>          
         <a href="#"><img id="logoo" class="img-logo" src="<%=request.getContextPath()%>/img/logo.png" alt=""></a>         
         <ul class="signin-links">
	       	<li><i style="margin-right:7px; color:#c15c16;" class="fas fa-child fa-1x"></i>${memVO.name} 您好<i style="color:#496b6b; margin: 0 10px 0 5px;" class="fas fa-exclamation"></i><a class="signin" href="<%=request.getContextPath()%>/Member.do?action=logout"><i class="fas fa-sign-out-alt"></i></a></li>
	     </ul> 
     </header>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div id="demo">
			<div class="search">
			<form action="<%=request.getContextPath()%>/activity/act.do"
				method="post" class="myForm">
				<div class="formGroup" style="display: inline-flex;">
				<jsp:useBean id="actCategoryService" scope="page" class="com.actCategory.model.ActCategoryService" />
					<label>找活動</label> 
					<select size="1" name="act_Category_Id">
						<option value="">請選擇活動名稱</option>
						<c:forEach var="actCategoryVO" items="${actCategoryService.all}">
							<option value="${actCategoryVO.actCategoryId}"
								${(activityVO.actCategoryId==actCategoryVO.actCategoryId)? 'selected':'' }>${actCategoryVO.actCategoryName}
							</option>
					
						</c:forEach>
					</select>
				</div>
				<div class="formGroup" style="display: inline-flex;">
					<label>輸入活動名稱</label> <input type="text" name="act_Name" value="">
				</div>
				<div class="formGroup" style="display: inline-flex;">
					<input type="date" name="act_Start_Date" id="pcsdate" value=""
						class="test1" placeholder="開始日期" size="14"> <label>到</label>
					<input type="date" name="act_End_Date" id="pcedate" value=""
						class="test2" placeholder="結束日期" size="14">
				</div>
				<input type="hidden" name="action" value="listEmps_ByCompositeQuery">
				<input type="hidden" name="from" value="front-end">
				<button type="submit" class="btn btn-default">搜尋</button>
			</form>

		</div>

<% 	if(list.size()==0){
		out.print("========================查無資料========================<br>");
		out.print("<br><br><br>");
		out.print("<a href='"+request.getContextPath()+"/front-end/activity/selectPage.jsp'> <i class='fas fa-home'></i>點我回活動列表</a>");
		out.print("<br><br><br>");
		out.print("======================================================<br>");
}
%>
	<c:forEach var="actVO" items="${list}">
			<div class="list">
				<div class="left">
					<jsp:useBean id="acPhSvc" scope="page"
						class="com.actPhoto.model.ActPhotoService" />
					<c:forEach var="actPhotoVO"
						items="${acPhSvc.getByActId(actVO.getActId())}" begin="0" end="0">
						<c:if test="${actPhotoVO.content != null}">	
						<img  src="<%=request.getContextPath()%>/actPhoto/ActPhotoServlet.do?action=showPhoto&photo=${actPhotoVO.actPhotoId}">
						</c:if>
						<c:if test="${actPhotoVO.content == null }">
						查無圖片
						</c:if>
					</c:forEach>
				</div>
				<div class="right">
					<div class="title">
						<h4>
							<a href="<%=request.getContextPath()%>/activity/act.do?action=getOne_For_Display&actId=${actVO.actId}&from=front-end" class="text-primary">${actVO.actName}</a>
						</h4>
					</div>
					<div class="content">${actVO.actInfo}</div>
				</div>
				<div class="sub">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/activity/act.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="活動詳情" class="btn btn-secondary btn-lg">
						<input type="hidden" name="actId" value="${actVO.actId}">
						<input type="hidden" name="action" value="getOne_For_Display">
						<input type="hidden" name="from" value="front-end">
					</FORM>
				</div>
			</div>
		</c:forEach>
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