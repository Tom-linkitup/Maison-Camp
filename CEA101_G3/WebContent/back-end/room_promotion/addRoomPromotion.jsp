<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room_promotion.model.*"%>
<%
    Room_promotionVO room_promotionVO = (Room_promotionVO) request.getAttribute("remain");
	String insertSuccess = (String)request.getAttribute("insertSuccess");
	String repeat = (String)request.getAttribute("repeat");
	
	request.getAttribute("room_promotionVO");
%>
	 		<!--  <input type="TEXT" name="room_category_id"  class="input-beautify" value="<%= (room_promotionVO==null)? "" : room_promotionVO.getRoom_category_id()%>"> -->
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>


<title>Insert title here</title>
<c:if test="${not empty errorMsgs}">
	<script>
		$("#tab-2").prop("checked",true);
		swal("新增失敗","請修正錯誤！","error");	
	</script>		
	</c:if>
	<c:if test="${repeat == 'repeat'}">
	<script>
		$("#tab-2").prop("checked",true);
		swal("資料未填寫完整","請修正錯誤！","error");
	</script>
	</c:if>
	<c:if test="${insertSuccess == 'yes' }">
		<script>
			$("#tab-2").prop("checked",true);
			swal("新增成功", "請查看新的訂房優惠編號", "success");
		</script>
	</c:if>
</head>
<body>
	<%-- 錯誤表列 --%>
	<c:forEach var="message" items="${errorMsgs}">
	</c:forEach>
<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/room_promotion/room_promotion.do" name="form1">
<div id="content-2">


		
 
		<tr><td>優惠房型設定:</td>
		<td><select size="1" name="room_category_id" class="input-beautify">
		<jsp:useBean id="roomTypeSvc" scope="page" class="com.roomtype.model.RoomTypeService" />
			<c:forEach var="roomTypeVO" items="${roomTypeSvc.allRT}">
				<option value="${roomTypeVO.room_category_id}" ${(room_promotionVO.room_category_id==roomTypeVO.room_category_id)? 'selected':'' } >${roomTypeVO.room_name}
			</c:forEach>
		</select></td>
		</tr>
		<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_category_id}</p>
		</tr>
		
		<tr><td>優惠資訊說明:</td>
		<td><input type="TEXT" name="room_promotion_info" class="input-beautify" value="<%=(room_promotionVO==null)? "" : room_promotionVO.getRoom_promotion_info()%>"></td>
		<c:if test="${not empty errorMsgs}">
		<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_promotion_info}</p>
		</c:if></tr><p></p>
		 
		<tr><td>優惠折扣設定:</td>
		<td><input type="NUMBER" min="0" max="1" step="0.01" name="room_discount"  class="input-beautify" value=""></td>
		<c:if test="${not empty errorMsgs}">
		<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_discount}</p>
		</c:if></tr><p></p>
		
		<tr><td>優惠開始日期:</td>
		<td><input name="room_prom_start_date" id="f_date1" type="text" class="input-beautify" value="" ></td>
		<c:if test="${not empty errorMsgs}">
		<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_prom_start_date}</p>
		</c:if></tr><p></p>
		
		<tr><td>優惠結束日期:</td>  
		<td><input name="room_prom_end_date" id="f_date2" type="text" class="input-beautify"  value="" ></td>
		<c:if test="${not empty errorMsgs}">
		<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_prom_end_date}</p>
		</c:if></tr><p></p>
		
		
		<input type="hidden" name="action" value="insert"><br>
		<button id="add" type="submit" class="btn btn-primary">新增訂房優惠</button> 

		</div>
	</FORM>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date room_prom_start_date = null;
  try {
	  room_prom_start_date = room_promotionVO.getRoom_prom_start_date();
   } catch (Exception e) {
	   room_prom_start_date = new java.sql.Date(System.currentTimeMillis());
   }
  
  java.sql.Date room_prom_end_date = null;
  try {
	  room_prom_end_date = room_promotionVO.getRoom_prom_end_date();
   }
  
  		catch (Exception e) {
	   room_prom_end_date = new java.sql.Date(System.currentTimeMillis());
   }
  
%>


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
		  //value: 
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:              '<%=room_prom_start_date%>'     
           // value:   new Date(),
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        }).on('changeDate', function (ev) {
            var startTime = $("#f_date1").val();
            $("#mEndTimeDiv").datetimepicker("setStartDate", startTime.toString("yyyy-MM-dd"));
            queryFunc();
        });
        
        
        
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   //value:
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:     'f_date1'          
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
       
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
    //        var somedate1 = new Date('');
    //         $('#f_date2').datetimepicker({
    //             beforeShowDay: function(date) {
    //            	  if (  date.getYear() <  somedate1.getYear() || 
    //    		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
    //    		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
    //                  ) {
    //                       return [false, ""]
    //                  }
    //                  return [true, ""];
    //          }});

        
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
