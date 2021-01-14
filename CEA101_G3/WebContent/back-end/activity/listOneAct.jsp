<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.activity.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	ActivityService actSvc = new ActivityService();
%>

<html>
<head>
<title>活動資料 - listOneAct.jsp</title>

<style>
  table {
	width:2000px; 
	overflow-x: auto; 
  }
  th{
	white-space:nowrap;
  }
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
<table id="table-1">
	<tr><td>
		 <h3>活動資料 - ListOneEmp.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/activity/selectPage.jsp"><img src="<%=request.getContextPath()%>/img/back.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動編號</th>
		<th>活動類別編號</th>
		<th>活動內容</th>
		<th>活動價格</th>
		<th>活動開始時間</th>
		<th>活動結束時間</th>
		<th>活動開始報名時間</th>
		<th>活動結束報名時間</th>
		<th>報名人數上限</th>
		<th>報名人數下限</th>
		<th>已報名人數</th>
		<th>活動名稱</th>
		<th>活動狀態</th>
		<th>活動折扣</th>
		<th>折扣內容</th>
		<th>折扣開始時間</th>
		<th>折扣結束時間</th>
	</tr>
	<tr>
		<td><%=activityVO.getActId()%></td>
		<td><%=activityVO.getActCategoryId()%></td>
		<td><%=activityVO.getActInfo()%></td>
		<td><%=activityVO.getActPrice()%></td>
		<td><%=activityVO.getActStartDate()%></td>
		<td><%=activityVO.getActEndDate()%></td>
		<td><%=activityVO.getActApplyOpen()%></td>
		<td><%=activityVO.getActApplyClose()%></td>
		<td><%=activityVO.getMaxPeople()%></td>
		<td><%=activityVO.getMinPeople()%></td>
		<td><%=activityVO.getActAlreadyApply()%></td>
		<td><%=activityVO.getActName()%></td>
		<td><%=activityVO.getActStatus()%></td>
		<td><%=activityVO.getActDiscount()%></td>
		<td><%=activityVO.getActPromInfo()%></td>
		<td><%=activityVO.getActPromStartDate()%></td>
		<td><%=activityVO.getActPromCloseDate()%></td>
	</tr>
</table>
</body>
</html>