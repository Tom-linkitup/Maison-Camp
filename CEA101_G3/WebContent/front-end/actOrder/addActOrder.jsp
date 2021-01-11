<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityOrder.model.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.member.model.*" %>

<%
	ActivityOrderVO aoVO = (ActivityOrderVO) request.getAttribute("activityOrderVO");
	ActivityService actSvc = new ActivityService();
	ActivityVO activityVO = actSvc.getOneActivity(request.getParameter("actId"));
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
	pageContext.setAttribute("activityVO", activityVO);
%>

<%
	java.sql.Date createTime = null;
	try {
		createTime = aoVO.getCreateTime();
	} catch (Exception e) {
		createTime = new java.sql.Date(System.currentTimeMillis());
	}
%>

<jsp:useBean id="actOrder" scope="page" class="com.activityOrder.model.ActivityOrderService" />

<c:set value="0" var="sum" />
	<c:forEach var="actOrderVO" items="${actOrder.findByActId(activityVO.actId)}">
		<c:set value="${sum + actOrderVO.people}" var="sum" />
	</c:forEach>

<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front-end/activity.css">
<link rel="shortcut icon" type="image/png" href="<%=request.getContextPath()%>/img/logo.png">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyui/demo/demo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<title>新增活動訂單</title>
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}

div.demo {
	height: 900px;
	text-align: center;
	background-image: url("<%=request.getContextPath()%>/img/bg1.jpg");
	opacity:85%;
	background-size:cover;
	padding-top:9%;
}

div.panel {
	margin: 0px auto;
}
#float-sidebar {
	position:absolute;
	left: 50%;
	top: 32%;
	width: 80px;
	text-align: center;
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
     </header>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class=demo >
		<form METHOD="post"	ACTION="<%=request.getContextPath()%>/actOrder/ActOrderServlet.do" name="form1" onSubmit="return CheckForm()">
			<div class="actinformation">
			<h1>活動資料</h1>
			<h3><span class="badge badge-secondary">活動名稱:<%=activityVO.getActName()%></span></h3>
			<h3><span class="badge badge-secondary">活動價格:<%=activityVO.getActPrice()%></span></h3>
			<h3><span class="badge badge-secondary">活動起始日期:<%=activityVO.getActStartDate()%></span></h3>
			<h3><span class="badge badge-secondary">活動結束日期:<%=activityVO.getActEndDate()%></span></h3>
			
			<hr>
			</div>
			<div style="margin: 20px 0;"></div>
			<div class="easyui-panel" title="報名資料" style="width: 100%; max-width: 400px; padding: 30px 60px;">
				<div style="margin-bottom: 20px">
				<input class="easyui-textbox" label="備註" name="note" labelPosition="top" value="<%=(aoVO == null) ? "無" : aoVO.getNote()%>"
						style="width: 100%;">
				</div>
				<div style="margin-bottom: 20px">
				
				
				
				<div style="margin-bottom: 20px">
				<h6>參加人數:</h6>
  				<select name="people" style="width:100px; height:40px;">
  						<c:forEach  var="sumpeople" begin="1" end="${activityVO.maxPeople-sum}">
  						  	<option value="${sumpeople}">${sumpeople}人</option>>
  						</c:forEach>
 				</select>
				</div>
				
				
				
				</div>
			
				<input type="hidden" name="actId" size="45" value="<%=activityVO.getActId()%>" /> 
				<input type="hidden" name="actPrice" size="45" value="<%=activityVO.getActPrice()%>" />
				<input type="hidden" name="memId" size="45" value="${memVO.mem_id}" /> 
				<input type="hidden" name="createTime" id="f_date1" /> 
				<input type="hidden" name="payment" size="45" value="刷卡" /> 
				<input type="hidden" name="status" size="45" value="0" /> 
				<input type="hidden" name="from" value="front-end"> 
				<input type="hidden" name="action" value="insertByMember">
				<div>
					<button type="submit" class="easyui-linkbutton" iconCls="icon-ok" style="width: 100%; height: 32px;">確定報名</button>
				</div>
			</div>
		</form>

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
        <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
		<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=createTime%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
	function CheckForm(){
		var yes = confirm('你確定要報名嗎？');
	  	if (yes) {
	  	return true;
	  	}else{
	  	return false;
	  	}
  	}	
</script>
</body>
</html>