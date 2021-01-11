<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actCategory.model.*"%>
<%
	ActCategoryVO actCategoryVO = (ActCategoryVO) request.getAttribute("actCategoryVO");
	List<ActCategoryVO> actCategoryList = new ActCategoryService().getAll();
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>活動類別資料修改 - update_actCateogory_input.jsp</title>

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
		 <h3>活動類別資料修改 - update_actCategory_input.jsp</h3>
		 <h4><a href="<%= request.getContextPath() %>/back-end/actcategory/selectPage.jsp"><img src="<%=request.getContextPath()%>/img/back.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/actcategory/activityCategory.do" name="form1">
<table>
	<tr>
		<td>活動類別編號:<font color=red><b>*</b></font></td>
		<td><%=actCategoryVO.getActCategoryId()%></td>
	</tr>
	<tr>
		<td>活動類別名稱:</td>
		<td><input type="TEXT" name="actCategoryName" size="45" value="<%=actCategoryVO.getActCategoryName()%>" /></td>
	</tr>			

						


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="actCategoryId" value="<%=actCategoryVO.getActCategoryId()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>