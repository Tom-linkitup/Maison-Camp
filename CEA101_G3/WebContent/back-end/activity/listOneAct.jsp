<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.activity.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	ActivityService actSvc = new ActivityService();
%>

<html>
<head>
<title>���ʸ�� - listOneAct.jsp</title>

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
		 <h3>���ʸ�� - ListOneEmp.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/back-end/activity/selectPage.jsp"><img src="<%=request.getContextPath()%>/img/back.png" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���ʽs��</th>
		<th>�������O�s��</th>
		<th>���ʤ��e</th>
		<th>���ʻ���</th>
		<th>���ʶ}�l�ɶ�</th>
		<th>���ʵ����ɶ�</th>
		<th>���ʶ}�l���W�ɶ�</th>
		<th>���ʵ������W�ɶ�</th>
		<th>���W�H�ƤW��</th>
		<th>���W�H�ƤU��</th>
		<th>�w���W�H��</th>
		<th>���ʦW��</th>
		<th>���ʪ��A</th>
		<th>���ʧ馩</th>
		<th>�馩���e</th>
		<th>�馩�}�l�ɶ�</th>
		<th>�馩�����ɶ�</th>
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
		<%
		String status ="";
		if(activityVO.getActStatus().equals(0)){
			status="���}�l���W";
		}else if(activityVO.getActStatus().equals(1)){
			status="���`";
		}else if(activityVO.getActStatus().equals(2)){
			status="����";
		}else if(activityVO.getActStatus().equals(3)){
			status="����";
		}
		%>
		<td><%= status %></td>
		<td><%=activityVO.getActDiscount()%></td>
		<td><%=activityVO.getActPromInfo()%></td>
		<td><%=activityVO.getActPromStartDate()%></td>
		<td><%=activityVO.getActPromCloseDate()%></td>
	</tr>
</table>
</body>
</html>