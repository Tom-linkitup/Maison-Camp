<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room_comment.model.*"%>

<%
Room_commentVO room_commentVO = (Room_commentVO) request.getAttribute("room_commentVO");
%>
<%= room_commentVO==null %> --${room_comment.VO.room_comment_id}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>房間評論資料新增 - addRoomComment.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>房間評論資料新增 - addRoomComment.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back-end/room_comment/select_page.jsp">
		 <img src="<%=request.getContextPath()%>/back-end/room_comment/images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room_comment/room_comment.do" name="form1">
<table>
	<tr>
		<td>房型:</td>
		<td><input type="TEXT" name="room_category_id" size="45" 
			 value="<%= (room_commentVO==null)? "" : room_commentVO.getRoom_category_id()%>" /></td>
	</tr>
	<tr>
		<td>評論內容:</td>
		<td><input type="TEXT" name="room_comment_content" size="45"
			 value="<%= (room_commentVO==null)? "" : room_commentVO.getRoom_comment_content()%>" /></td>
	</tr>
	<tr>
		<td>評論時間:</td>
		<td><input name="time" id="f_date1" type="text"
		value="<%= (room_commentVO==null)?"": room_commentVO.getTime()%>"  /></td>
	</tr>
	
	


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>




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


</html>