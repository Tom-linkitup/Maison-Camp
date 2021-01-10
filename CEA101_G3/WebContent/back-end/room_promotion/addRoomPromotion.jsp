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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/back-end/room_promotion.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<meta charset="UTF-8">


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
<FORM id="myAddForm" METHOD="post" ACTION="${pageContext.request.contextPath}/room_promotion/room_promotion.do" name="form1">
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
		<td><input type="NUMBER" min="0" max="1" step="0.01" name="room_discount"  class="input-beautify" value="<%=(room_promotionVO==null)? "" : room_promotionVO.getRoom_discount()%>"></td>
		<c:if test="${not empty errorMsgs}">
		<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_discount}</p>
		</c:if></tr><p></p>
		
		<tr><td>優惠開始日期:</td>
		<td><input name="room_prom_start_date" id="f_date1" type="text" class="input-beautify" value="<%=(room_promotionVO==null)? "" : room_promotionVO.getRoom_prom_start_date()%>"></td>
		<c:if test="${not empty errorMsgs}">
		<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_prom_start_date}</p>
		</c:if></tr><p></p>
		
		<tr><td>優惠結束日期:</td>  
		<td><input name="room_prom_end_date" id="f_date2" type="text" class="input-beautify"  value="<%=(room_promotionVO==null)? "" : room_promotionVO.getRoom_prom_end_date()%>" ></td>
		<c:if test="${not empty errorMsgs}">
		<p class="error" style="color:red; font-size:8px;">${errorMsgs.room_prom_end_date}</p>
		</c:if></tr><p></p>
		
		<input type="hidden" name="action" value="insert"><br>
		<button id="add" type="submit" class="btn btn-primary">新增訂房優惠</button> 
		</div>
		
	</FORM>
	
	
	
	
</body>



<script>
$(function() {
	   var dateFormat = "mm/dd/yy",
	     from = $("#f_date1")
	     .datepicker({
	       defaultDate: "+1w",
	       changeMonth: true,
	       changeYear: true,
	       minDate: 'SYSDATE',
	       numberOfMonths: 1
	     })
	     .on("change", function() {
	       to.datepicker("option", "minDate", getDate(this));
	     }),
	     to = $("#f_date2").datepicker({
	       defaultDate: "+1w",
	       changeMonth: true,
	       changeYear: true,
	       minDate: 'SYSDATE',
	       numberOfMonths: 1
	     })
	     .on("change", function() {
	       from.datepicker("option", "maxDate", getDate(this));
	     });

	   function getDate(element) {
	     var date;
	     try {
	       date = $.datepicker.parseDate(dateFormat, element.value);
	     } catch (error) {
	       date = null;
	     }

	     return date;
	   }
	   
	   $("#showTo").click(function() {
	     $("#from").datepicker("show");
	   });
	   
	   $("#add").on('click', function(e){
		   e.preventDefault(); // 預設行為

		   let date1 = new Date(getDate($('#f_date1')[0]));
	       let str = date1.getFullYear() + "-" + (date1.getMonth() + 1) + "-"+ date1.getDate();
	       $("#f_date1").val(str);
	       
	       let date2 = new Date(getDate($('#f_date2')[0]));
	       let str2 = date2.getFullYear() + "-" + (date2.getMonth() + 1) + "-"+ date2.getDate();
	       $("#f_date2").val(str2);
	       
	       $('#myAddForm').submit(); // trigger
	       
	   })
	 });
</script>

</html>
