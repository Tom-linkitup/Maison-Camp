<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityOrder.model.*"%>

<%
  ActivityOrderVO aoVO = (ActivityOrderVO) request.getAttribute("activityOrderVO");
%>


<!DOCTYPE html>
<html>
<head>
<title>修改活動訂單</title>
</head>
<body>
<h4><a href="<%=request.getContextPath()%>/back-end/actOrder/actOrder_select_page.jsp">回首頁</a></h4>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actOrder/ActOrderServlet.do" name="form1">
<table>

	<tr>
		<td>活動訂單編號:</td>
		<td><input type="TEXT" name="actOrderId" size="45" 
			 value="<%=aoVO.getActOrderId()%>" readonly/></td>
	</tr>

	<tr>
		<td>活動編號:</td>
		<td><input type="TEXT" name="actId" size="45" 
			 value="<%= (aoVO==null)? "請輸入活動編號" : aoVO.getActId()%>" /></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memId" size="45"
			 value="<%= (aoVO==null)? "請輸入會員編號" : aoVO.getMemId()%>" readonly/></td>
	</tr>
	<tr>
		<td>訂單生成日期:</td>
		<td><input name="createTime" id="f_date1" type="text" readonly></td>
	</tr>
	<tr>
		<td>備註:</td>
		<td><input type="TEXT" name="note" size="45"
			 value="<%= (aoVO==null)? "無" : aoVO.getNote()%>" /></td>
	</tr>
	<tr>
		<td>人數:</td>
		<td><input type="TEXT" name="people" size="45"
			 value="<%= (aoVO==null)? "0" : aoVO.getPeople()%>" /></td>
	</tr>
	<tr>
		<td>付款方式:</td>
		<td><input type="TEXT" name="payment" size="45"
			 value="<%= (aoVO==null)? "0" : aoVO.getPayment()%>" /></td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td>
		<select size="1" name="status">
		<option value="0">已確認</option>
		<option value="1">已取消</option>
		<option value="2">待評價</option>
		<option value="3">已完成</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>活動價格:</td>
		<td><input type="TEXT" name="actPrice" size="45"
			 value="<%= (aoVO==null)? "0" : aoVO.getActPrice()%>" /></td>
	</tr>
	


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="submit" value="送出修改"></FORM>




</body>


<% 
  java.sql.Date createTime = null;
  try {
	    createTime = aoVO.getCreateTime();
   } catch (Exception e) {
	    createTime = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
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