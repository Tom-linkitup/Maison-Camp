<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.room_comment.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Room_commentVO room_commentVO = (Room_commentVO) request.getAttribute("room_commentVO"); //Room_commentServlet.java(Concroller), 存入req的room_commentVO物件
%>


<html>
<head>
<title>額外消費資料 - listOneRoomComment.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listOneRoomComment.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/room_comment/select_page.jsp">
		 <img src="<%=request.getContextPath()%>/back-end/room_comment/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>房間評論編號</th>
		<th>房型</th>
		<th>評論內容</th>
		<th>評論時間</th>
		
	</tr>

		<tr>
				<td><%=room_commentVO.getRoom_comment_id()%></td>
				<td><%=room_commentVO.getRoom_category_id()%></td>
				<td><%=room_commentVO.getRoom_comment_content()%></td>
				<td><%=room_commentVO.getTime()%></td>
			
			
			
		</tr>
	
</table>


</body>
</html>