<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
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
	border-radius: 3em;
	margin: 20px auto;
	position:relative;
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


	<jsp:include page="/html/top.jsp" flush="true" />



	<!-- 左邊活動資訊 -->
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
							<i class="fas fa-user-check"></i>已報名人數:<%=activityVO.getActAlreadyApply()%>
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
				<input id="addOrder" type="button" value="我要報名"
					class="btn btn-primary"
					onclick="location.href='<%=request.getContextPath()%>/front-end/actOrder/addActOrder.jsp?actId=<%=activityVO.getActId()%>&memId=M10001'">
			</div>
		</div>


		<!-- 右邊評論 -->
		<div class="col-5 test comment">
			<h3 class="text-primary">活動評論</h3>
			<jsp:useBean id="actCommentSvc" scope="page"
				class="com.activityComment.model.ActivityCommentService" />
			<c:forEach var="actCommentVO"
				items="${actCommentSvc.getByActCategoryId(activityVO.actCategoryId)}">
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

	<jsp:include page="/html/footer.jsp" flush="true" />
</body>
</html>