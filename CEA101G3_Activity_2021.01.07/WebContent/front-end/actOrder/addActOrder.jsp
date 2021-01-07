<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityOrder.model.*"%>
<%@ page import="com.activity.model.*"%>

<%
	ActivityOrderVO aoVO = (ActivityOrderVO) request.getAttribute("activityOrderVO");
	ActivityService actSvc = new ActivityService();
	ActivityVO activityVO = actSvc.getOneActivity(request.getParameter("actId"));
%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyui/demo/demo.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
<title>新增活動訂單</title>
<style>
div.demo {
	margin: 5px 0px;
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
<script>
function CheckForm(){
	var yes = confirm('你確定要報名嗎？');
  	if (yes) {
  	return true;
  	}else{
  	return false;
  	}
}	
</script>
</head>
<body>
	<jsp:include page="/html/top.jsp" flush="true" />
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
				<input class="easyui-textbox" label="參加人數" labelPosition="top" name="people" value="<%=(aoVO == null) ? "1" : aoVO.getPeople()%>" style="width: 100%;">
				</div>
			
				<input type="hidden" name="actId" size="45" value="<%=activityVO.getActId()%>" /> 
				<input type="hidden" name="actPrice" size="45" value="<%=activityVO.getActPrice()%>" />
				<input type="hidden" name="memId" size="45" value="<%=request.getParameter("memId")%>" /> 
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
	
	<jsp:include page="/html/footer.jsp" flush="true" />
</body>


<%
	java.sql.Date createTime = null;
	try {
		createTime = aoVO.getCreateTime();
	} catch (Exception e) {
		createTime = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

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
</script>


</html>