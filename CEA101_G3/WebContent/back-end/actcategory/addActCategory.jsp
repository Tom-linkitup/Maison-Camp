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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�������O��Ʒs�W - addEmp.jsp</title>

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
	width: 500px;
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
<body>
<div id="content-2">
<h3>�s�W�������O</h3>


	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/actcategory/activityCategory.do" name="form1">
		<table>
			<tr>
				<td>�������O�s��:</td>
				<td><input type="TEXT" name="actCategoryId" size="45"
					value="" placeholder="�п�J�������O�s��  EX: ACT_CATEGORY1" /></td>
			</tr>
			<tr>
				<td>�������O�W��:</td>
				<td><input type="TEXT" name="actCategoryName" size="45"
					value="<%=(actCategoryVO == null) ? "" : actCategoryVO.getActCategoryName()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</FORM>
</div>
</body>
</html>